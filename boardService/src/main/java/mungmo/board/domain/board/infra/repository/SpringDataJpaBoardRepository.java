package mungmo.board.domain.board.infra.repository;

import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaBoardRepository extends JpaRepository<BoardEntity, Long> {
    Page<BoardEntity> findAllBySubject(Subject subject, Pageable pageable);
}
