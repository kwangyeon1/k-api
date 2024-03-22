package api.freelive.board.adapter.in.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private int status;

    private String message;

    private T data;

    public ApiResponse(T data) {
        this(data, 200); // 기본적으로 status를 200으로 설정
    }

    public ApiResponse(T data, int status){
        this.data = data;
        this.status = status;
    }

}
