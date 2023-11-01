package api.freelive.board.adapter.out.persistence.adapter;

import api.freelive.board.adapter.out.persistence.model.entity.PostFileJpa;
import api.freelive.board.adapter.out.persistence.model.entity.PostJpa;
import api.freelive.board.adapter.out.persistence.repository.PostFileJpaRepository;
import api.freelive.board.adapter.out.persistence.repository.PostJpaRepository;
import api.freelive.board.application.port.out.SavePostPort;
import api.freelive.board.domain.Post;
import api.freelive.board.domain.PostFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostJpaAdapter implements SavePostPort {

    private final PostJpaRepository postJpaRepository;
    private final PostFileJpaRepository postFileJpaRepository;

    @Override
    public Long save(Post post) {
        PostJpa entity = PostJpaMapper.toEntity(post);
        PostJpa postJpa = postJpaRepository.save(entity);
        return postJpa.getPostNum();
    }

    @Override
    public void savePostFile(PostFile postFile) {
        PostFileJpa entity = PostJpaMapper.toEntity(postFile);
        postFileJpaRepository.save(entity);
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

}
