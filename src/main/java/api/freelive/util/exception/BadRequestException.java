package api.freelive.util.exception;

import api.freelive.util.message.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RestApiException {
    private final ErrorMessage errorCode;

    public BadRequestException(ErrorMessage error) {
        super(HttpStatus.BAD_REQUEST, error.getMessage());
        this.errorCode = error;
    }
}

