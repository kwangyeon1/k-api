package api.freelive.board.adapter.out.persistence.repository;

import api.freelive.board.adapter.out.persistence.model.entity.PostFileJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileJpaRepository extends JpaRepository<PostFileJpa, Long> {

}
