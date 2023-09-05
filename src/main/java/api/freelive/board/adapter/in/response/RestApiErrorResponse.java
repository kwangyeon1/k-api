package api.freelive.board.adapter.in.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestApiErrorResponse {
    private final int status;
    private final String message;
}
