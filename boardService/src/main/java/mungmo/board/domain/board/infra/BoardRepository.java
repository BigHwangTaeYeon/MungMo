package mungmo.board.domain.board.infra;

import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardRepository {
    Page<BoardEntity> findAllBySubject(Subject subject, Pageable pageable);

    Optional<BoardEntity> findById(Long id);

    void save(BoardEntity entity);

    void delete(BoardEntity entity);
}
