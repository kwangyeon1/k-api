package api.freelive.board.application.port.in;

import api.freelive.board.domain.User;
import api.freelive.board.dto.PostWriteReqDto;

import java.util.List;

public interface EditPostUseCase {

    Long writePost(PostWriteReqDto postReqDto, User user);

    void deletePost(Long postNum, User user);

    void modifyPost(PostWriteReqDto postReqDto, User user);

    void removePostFiles(Long postNum, List<Long> postFileNums);

}
