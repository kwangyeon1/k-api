package api.freelive.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionValues {

//    @Value("${jwt.secret}") private String secretKey;
//
//    @Value("${password.salt}") private String salt;

    public static String secretKey;

    public static String salt;

        @Value("${jwt.secret}")
    public void setSecretKey(String value) {
        secretKey = value;
    }

    @Value("${password.salt}")
    public void setSalt(String value) {
        salt = value;
    }

}
