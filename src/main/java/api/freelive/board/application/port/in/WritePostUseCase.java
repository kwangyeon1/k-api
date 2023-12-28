package api.freelive.board.application.port.in;

import api.freelive.board.domain.User;
import api.freelive.board.dto.PostReqDto;

public interface WritePostUseCase {

    Long writePost(PostReqDto postReqDto, User user);

}
