package api.freelive.board.adapter.in;

import api.freelive.board.adapter.in.response.AuthTokenResponse;
import api.freelive.board.application.port.in.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> param){
        String userId = param.get("userId");
        String password = param.get("password");
        Object auth = authUseCase.login(userId, password);

        return ResponseEntity.ok(auth);
    }

    @PostMapping("/test-login")
    public ResponseEntity<?> swaggerAuthToken(@RequestBody Map<String, String> param){
        String userNum = param.get("userNum");
        String token = authUseCase.createToken(userNum);

        return ResponseEntity.ok(new AuthTokenResponse(token));
    }

}
