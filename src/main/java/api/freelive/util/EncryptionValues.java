package api.freelive.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionValues {

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
