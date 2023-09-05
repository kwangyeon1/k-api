package api.freelive.util.exception;

import api.freelive.util.message.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RestApiException {
    private final ErrorMessage errorCode;

    public NotFoundException(ErrorMessage error) {
        super(HttpStatus.NOT_FOUND, error.getMessage());
        this.errorCode = error;
    }
}
