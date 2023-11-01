package api.freelive.board.application.service;

import api.freelive.board.application.port.in.WritePostUseCase;
import api.freelive.board.application.port.out.SavePostPort;
import api.freelive.board.application.port.out.StoreFilePort;
import api.freelive.board.domain.Post;
import api.freelive.board.domain.PostFile;
import api.freelive.board.domain.User;
import api.freelive.board.dto.PostReqDto;
import api.freelive.util.exception.BadRequestException;
import api.freelive.util.exception.DefaultException;
import api.freelive.util.message.ErrorMessage;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService implements WritePostUseCase {

    private final SavePostPort savePostPort;

    private final StoreFilePort storeFilePort;

    @Override
    @Transactional
    public Long writePost(PostReqDto postReqDto, User user) {
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
            String absoluteFilePath = storeFilePort.store(postReqDto.getFile());
            PostFile postFile = new PostFile(postNum, absoluteFilePath);
            savePostPort.savePostFile(postFile);
        } catch (Exception e) {
            throw new DefaultException("문제가 생겼습니다: " + e.getMessage());
        }

        return postNum;
    }

}
