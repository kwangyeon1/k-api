package api.freelive.board.application.port.in;

import api.freelive.board.domain.User;
import api.freelive.board.dto.PostWriteReqDto;

public interface WritePostUseCase {

    Long writePost(PostWriteReqDto postReqDto, User user);

}
