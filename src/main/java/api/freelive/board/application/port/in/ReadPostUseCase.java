package api.freelive.board.application.port.in;

import api.freelive.board.domain.Post;
import api.freelive.board.dto.PostDto;
import org.springframework.data.domain.Page;

public interface ReadPostUseCase {

    Post readPost(PostDto postDto);

    Page<Post> readPosts(String page);

}