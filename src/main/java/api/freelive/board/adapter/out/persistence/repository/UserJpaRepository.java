package api.freelive.board.adapter.out.persistence.repository;

import api.freelive.board.adapter.out.persistence.model.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {

    UserJpa findByUserId(String userId);

}
