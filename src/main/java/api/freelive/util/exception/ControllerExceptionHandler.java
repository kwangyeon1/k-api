package api.freelive.util.exception;

import api.freelive.board.adapter.in.response.RestApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        RestApiErrorResponse errorResponse = new RestApiErrorResponse(
                HttpServletResponse.SC_NOT_FOUND,
                e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        RestApiErrorResponse errorResponse = new RestApiErrorResponse(
                HttpServletResponse.SC_OK,
                e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<?> handleBadRequestException(StorageException e) {
        RestApiErrorResponse errorResponse = new RestApiErrorResponse(
                HttpServletResponse.SC_OK,
                e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<?> handleBadRequestException(DefaultException e) {
        RestApiErrorResponse errorResponse = new RestApiErrorResponse(
                HttpServletResponse.SC_OK,
                e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

}
