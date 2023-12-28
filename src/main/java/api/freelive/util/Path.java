package api.freelive.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Path {

    public static String post;

    @Value("${path.url.post}")
    public void setPost(String value) {
        post = value;
    }

}
