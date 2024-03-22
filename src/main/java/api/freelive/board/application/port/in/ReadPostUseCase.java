package api.freelive.board.application.port.in;

import api.freelive.board.domain.Post;
import org.springframework.data.domain.Page;

public interface ReadPostUseCase {

    Post readPost(Long postNum);

    Page<Post> readPosts(String page);

}