package api.freelive.util.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    /* 200  리소스를 찾을 수 없음 */
    USER_NOT_FOUND("해당 사용자를 찾을 수 없습니다.", HttpStatus.OK),
    INVALID_PASSWORD("패스워드가 틀렸습니다.", HttpStatus.OK),

    /* 400 BAD_REQUEST 잘못된 요청 */
    BAD_REQUEST_BODY("필요한 요청 값이 비어있거나 잘못되었습니다.", HttpStatus.BAD_REQUEST),

    /* 401 UNAUTHORIZED 사용자 인증 실패 */
    INVALID_JWT("JWT가 없거나 유효하지 않습니다.", HttpStatus.UNAUTHORIZED),


    /* 404 NOT_FOUND  리소스를 찾을 수 없음 */
    NOT_FOUND("리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);


    /* Riot Api Error Message */

    private final String message;
    private final HttpStatus httpStatus;


}
