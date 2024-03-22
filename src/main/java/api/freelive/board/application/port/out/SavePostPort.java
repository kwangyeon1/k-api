package api.freelive.board.application.port.out;

import api.freelive.board.domain.Post;

public interface SavePostPort {

    Long save(Post post);

    void delete(Post post);

}
