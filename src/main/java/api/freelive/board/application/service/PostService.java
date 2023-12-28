package api.freelive.board.application.service;

import api.freelive.board.application.port.in.ReadPostUseCase;
import api.freelive.board.application.port.in.WritePostUseCase;
import api.freelive.board.application.port.out.LoadPostPort;
import api.freelive.board.application.port.out.SavePostPort;
import api.freelive.board.application.port.out.StoreFilePort;
import api.freelive.board.domain.Post;
import api.freelive.board.domain.PostFile;
import api.freelive.board.domain.User;
import api.freelive.board.dto.PostDto;
import api.freelive.board.dto.PostWriteReqDto;
import api.freelive.util.FileFilter;
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

@Service
@RequiredArgsConstructor
public class PostService implements ReadPostUseCase, WritePostUseCase {

    private final LoadPostPort loadPostPort;

    private final SavePostPort savePostPort;

    private final StoreFilePort storeFilePort;

    @Override
    public Post readPost(PostDto postDto) {
        Post post = loadPostPort.load(postDto.getPostNum());
        return post;
    }

    @Override
    public Page<Post> readPosts(String page) {
        int pageNumber = Integer.parseInt(page); // 페이지 번호를 정수로 변환
        int pageSize = 10; // 한 페이지당 표시할 게시물 수 (원하는 값으로 조정)

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

        Post post = Post.builder()
                .userNum(user.getUserNum())
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .build();

        Long postNum = 0L;
        try {
            postNum = savePostPort.save(post);

            MultipartFile file = postReqDto.getFile();
            if (file.isEmpty()) {
                throw new StorageException("파일이 없습니다.");
            }

            // 파일 확장자 확인
            String fileExtension = FileFilter.getFileExtension(file.getOriginalFilename());
            if (!FileFilter.isAllowedImageExtension(fileExtension)) {
                throw new StorageException("지원하지 않는 이미지 형식입니다.");
            }

            storeFilePort.store(postReqDto.getFile());
            PostFile postFile = new PostFile(postNum, Path.post + postReqDto.getFile().getOriginalFilename());
            savePostPort.savePostFile(postFile);
        } catch (Exception e) {
            throw new DefaultException("문제가 생겼습니다: " + e.getMessage());
        }

        return postNum;
    }

}
