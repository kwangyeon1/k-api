package api.freelive.board.adapter.out.persistence.repository;

import api.freelive.board.adapter.out.persistence.model.entity.PostJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostJpaRepository extends JpaRepository<PostJpa, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM PostFileJpa pf WHERE pf.postJpa IS NULL")
    void deleteOrphanedPostFiles(PostJpa postJpa);

    Page<PostJpa> findAllByUserNum(Long userNum, Pageable pageable);

}
