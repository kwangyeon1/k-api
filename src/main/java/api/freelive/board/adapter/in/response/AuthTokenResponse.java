package api.freelive.board.adapter.in.response;

import lombok.Getter;

@Getter
public class AuthTokenResponse {

    private String token;

    public AuthTokenResponse(String token){
        this.token = token;
    }
}
