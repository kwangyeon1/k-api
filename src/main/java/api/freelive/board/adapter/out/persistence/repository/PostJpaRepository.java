package api.freelive.board.adapter.out.persistence.repository;

import api.freelive.board.adapter.out.persistence.model.entity.PostJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostJpa, Long> {

}
