package api.freelive.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Auth {

    private String encryptedPassword;

    private String token;

    public Auth(String token){
        this.token = token;
    }

    public void setEncryptedPassword(String value) {
        this.encryptedPassword = value;
    }

    public void setToken(String value) {
        this.token = value;
    }

}
