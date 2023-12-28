package api.freelive.board.application.port.in;

import api.freelive.board.dto.JoinReqDto;
import api.freelive.board.dto.UserDto;

public interface JoinUseCase {

    UserDto join(JoinReqDto joinReqDto);

}
