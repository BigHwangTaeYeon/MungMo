package mungmo.board.domain.board.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.board.domain.board.entity.CommentEntity;
import mungmo.board.domain.board.infra.CommentRepository;
import mungmo.board.domain.board.infra.repository.SpringDataJpaCommentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentAdapter implements CommentRepository {
    private final SpringDataJpaCommentRepository commentRepository;

    @Override
    public Optional<CommentEntity> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public void save(CommentEntity of) {
        commentRepository.save(of);
    }

    @Override
    public void delete(CommentEntity entity) {
        commentRepository.delete(entity);
    }
}
