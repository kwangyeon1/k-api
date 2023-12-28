package api.freelive.board.application.port.out;

import api.freelive.board.domain.Post;
import api.freelive.board.domain.PostFile;

public interface SavePostPort {

    Long save(Post post);

    void savePostFile(PostFile postFile);

}
