package mungmo.board.domain.board.infra;

import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.entity.CommentEntity;

import java.util.Optional;

public interface CommentRepository {
    Optional<CommentEntity> findById(Long commentId);

    void save(CommentEntity of);

    void delete(CommentEntity entity);
}
