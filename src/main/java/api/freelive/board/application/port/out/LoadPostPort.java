package api.freelive.board.application.port.out;

import api.freelive.board.domain.Post;
import api.freelive.board.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LoadPostPort {

    Post load(Long num);

    Optional<Page<Post>> getAll(Pageable page);

    Optional<Page<Post>> getAllByUser(Pageable page, User user);

}
