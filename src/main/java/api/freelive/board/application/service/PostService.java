package api.freelive.board.application.service;

import api.freelive.board.adapter.out.persistence.model.entity.PostFileJpa;
import api.freelive.board.application.port.in.ReadPostUseCase;
import api.freelive.board.application.port.in.EditPostUseCase;
import api.freelive.board.application.port.out.LoadPostPort;
import api.freelive.board.application.port.out.SavePostPort;
import api.freelive.board.application.port.out.StoreFilePort;
import api.freelive.board.domain.Post;
import api.freelive.board.domain.User;
import api.freelive.board.dto.PostWriteReqDto;
import api.freelive.util.FileUtil;
import api.freelive.util.Path;
import api.freelive.util.exception.BadRequestException;
import api.freelive.util.exception.DefaultException;
import api.freelive.util.exception.StorageException;
import api.freelive.util.message.ErrorMessage;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService implements ReadPostUseCase, EditPostUseCase {

    private final LoadPostPort loadPostPort;

    private final SavePostPort savePostPort;

    private final StoreFilePort storeFilePort;

    @Override
    public Post readPost(Long postNum) {
        Post post = loadPostPort.load(postNum);
        return post;
    }

    @Override
    public Page<Post> readPosts(String page) {
        int pageNumber = Integer.parseInt(page); // 페이지 번호를 정수로 변환
        int pageSize = 5; // 한 페이지당 표시할 게시물 수 (원하는 값으로 조정)

        Page<Post> postPage = loadPostPort.getAll(PageRequest.of(pageNumber - 1, pageSize))
                .orElseThrow(()->new BadRequestException(ErrorMessage.USER_NOT_FOUND));

        return postPage;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Long writePost(PostWriteReqDto postReqDto, User user) {
        if (StringUtil.isNullOrEmpty(postReqDto.getTitle()) || StringUtil.isNullOrEmpty(postReqDto.getContent())) {
            throw new BadRequestException(ErrorMessage.BAD_REQUEST_BODY);
        }

        Long postNum = 0L;
        try {
            MultipartFile file = postReqDto.getFile();
            if(file == null){ // 업로드 요청한 파일이 없다면
                throw new StorageException("파일이 없습니다.");
            }

            String fileName = FileUtil.rename(file);
            this.storeAndCheckIfPostFileIsValid(file, fileName); // 파일 확장자 확인 후, 물리적인 저장소에 저장

            //PostFile 생성
            PostFileJpa postFile = new PostFileJpa(Path.post + fileName, fileName);
            List<PostFileJpa> postFiles = Arrays.asList(postFile); //첨부파일을 리스트화 (추후 다중파일이 1개의 게시글에 업로드 될 수 있기 때문에)

            Post post = new Post(user.getUserNum(), postReqDto.getTitle(), postReqDto.getContent(), postFiles);
            postNum = savePostPort.save(post);
        } catch (Exception e) {
            throw new DefaultException("문제가 생겼습니다: " + e.getMessage());
        }

        return postNum;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deletePost(Long postNum, User user) {
        try {
            Post post = loadPostPort.load(postNum);
            if(post.getUserNum() != user.getUserNum()){
                throw new StorageException("해당 게시글 작성자가 아닙니다.");
            }

            this.removeAllPostFilesByFileManager(post); // 물리적인 저장소의 파일들을 삭제
            savePostPort.delete(post); // Post삭제 (연관된 PostFile도 같이 삭제된다)
        } catch (Exception e) {
            throw new DefaultException("문제가 생겼습니다: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void modifyPost(PostWriteReqDto postReqDto, User user) {
        if (StringUtil.isNullOrEmpty(postReqDto.getP_id()) || StringUtil.isNullOrEmpty(postReqDto.getTitle()) || StringUtil.isNullOrEmpty(postReqDto.getContent())) {
            throw new BadRequestException(ErrorMessage.BAD_REQUEST_BODY);
        }

        try {
            Post post = loadPostPort.load(Long.parseLong(postReqDto.getP_id()));
            if(post.getUserNum() != user.getUserNum()){
                throw new StorageException("해당 게시글 작성자가 아닙니다.");
            }

            if(postReqDto.getRemoveFileIds() != null){ //삭제 요청한 PostFile들이 있는 경우
                List<String> fileNames = post.removePostFiles(postReqDto.getRemoveFileIds()); // 삭제 요청한 PostFile들을 제거
                storeFilePort.deletePostFiles(fileNames); // 물리적인 파일을 제거
            }

            MultipartFile file = postReqDto.getFile();
            if (file != null) { // 업로드 요청한 파일이 있는경우
                String fileName = FileUtil.rename(file);
                this.storeAndCheckIfPostFileIsValid(file, fileName); // 파일 확장자 확인 후, 물리적인 저장소에 저장

                //PostFile 생성
                PostFileJpa postFile = new PostFileJpa(Path.post + fileName, fileName);
                //첨부파일을 리스트화 (추후 다중파일이 1개의 게시글에 업로드 될 수 있기 때문에)
                List<PostFileJpa> postFiles = Arrays.asList(postFile);

                post.addPostFiles(postFiles); // 새로운 PostFile를 추가
            }
            post.setTitle(postReqDto.getTitle());
            post.setContent(postReqDto.getContent());

            savePostPort.save(post);
        } catch (Exception e) {
            throw new DefaultException("문제가 생겼습니다: " + e.getMessage());
        }
    }

    /**
     * (공통) 유효성검사후, 파일을 물리적으로 저장
     */
    public String storeAndCheckIfPostFileIsValid(MultipartFile file, String fileName){
        String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
        if (!FileUtil.isAllowedImageExtension(fileExtension)) { // 파일 확장자 확인
            throw new StorageException("지원하지 않는 이미지 형식입니다.");
        }

        return storeFilePort.storePostFile(file, fileName);
    }

    /**
     * (공통) 모든 파일을 물리적으로 삭제
     */
    public void removeAllPostFilesByFileManager(Post post){
        List<String> fileNames = post.getPostFiles().stream().map(PostFileJpa::getName)
                .collect(Collectors.toList());
        storeFilePort.deletePostFiles(fileNames);
    }

    // 게시글의 파일 삭제만 하기
    @Override
    public void removePostFiles(Long postNum, List<Long> postFileNums){
        Post post = loadPostPort.load(postNum);

        List<String> fileNames = post.removePostFiles(postFileNums); // PostFile을 삭제하고 삭제한 PostFile의 name을 받아서 초기화
        storeFilePort.deletePostFiles(fileNames); // 파일의 이름을 통해 물리적으로 파일 삭제

        savePostPort.save(post);
    }

}
