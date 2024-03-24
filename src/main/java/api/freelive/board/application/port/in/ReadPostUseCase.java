package api.freelive.board.application.port.in;

import api.freelive.board.domain.Post;
import api.freelive.board.domain.User;
import org.springframework.data.domain.Page;

public interface ReadPostUseCase {

    Post readPost(Long postNum);

    Page<Post> readPosts(String page);

    Page<Post> readMyPosts(String page, User user);

}