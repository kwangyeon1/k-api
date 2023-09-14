package api.freelive.board.adapter.in;

import api.freelive.board.application.port.in.AuthUseCase;
import api.freelive.board.application.port.in.JoinUseCase;
import api.freelive.board.dto.JoinReqDto;
import api.freelive.board.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JoinUseCase joinUseCase;

    private final AuthUseCase authUseCase;

    @PostMapping(path = "/join", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> join(@ModelAttribute JoinReqDto joinReqDto) {

        UserDto userdto = joinUseCase.join(joinReqDto);
        Object auth = authUseCase.login(userdto.getUserId(), userdto.getPw());

        return ResponseEntity.ok(auth);
    }

}
