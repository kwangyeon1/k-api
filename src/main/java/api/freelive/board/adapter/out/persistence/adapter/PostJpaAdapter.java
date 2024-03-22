package api.freelive.board.adapter.out.persistence.adapter;

import api.freelive.board.adapter.out.persistence.model.entity.PostJpa;
import api.freelive.board.adapter.out.persistence.repository.PostJpaRepository;
import api.freelive.board.application.port.out.LoadPostPort;
import api.freelive.board.application.port.out.SavePostPort;
import api.freelive.board.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostJpaAdapter implements LoadPostPort, SavePostPort {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Long save(Post post) {
        PostJpa entity = PostJpaMapper.toEntity(post);
        PostJpa postJpa = postJpaRepository.save(entity);
        postJpaRepository.deleteOrphanedPostFiles(entity);
        return postJpa.getPostNum();
    }

    @Override
    public void delete(Post post) {
        postJpaRepository.deleteById(post.getPostNum());
    }

    @Override
    public Post load(Long num) {
        PostJpa entity = postJpaRepository.findById(num)
                .orElseThrow(NoSuchElementException::new);

        return PostJpaMapper.toDomain(entity);
    }

    @Override
    public Optional<Page<Post>> getAll(Pageable pageable) {
        Page<PostJpa> postPage = postJpaRepository.findAll(pageable);
        if(postPage == null){
            return Optional.empty();
        }

        return Optional.ofNullable(postPage.map(PostJpaMapper::toDomain));
    }

}
