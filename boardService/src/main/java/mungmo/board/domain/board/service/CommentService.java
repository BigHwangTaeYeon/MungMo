package mungmo.board.domain.board.service;

import lombok.RequiredArgsConstructor;
import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.entity.CommentEntity;
import mungmo.board.domain.board.infra.CommentRepository;
import mungmo.board.domain.board.infra.repository.SpringDataJpaCommentRepository;
import mungmo.board.response.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentEntity comment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }

    public void commentResister(String comment, BoardEntity boardEntity, MemberEntity memberEntity) {
        commentRepository.save(CommentEntity.of(comment, boardEntity, memberEntity));
    }

    public void commentDelete(Long userId, CommentEntity entity) throws IllegalAccessException {
        if(!userId.equals(entity.getWriter().getId())) {
            throw new IllegalAccessException();
        }
        commentRepository.delete(entity);
    }

    public void commentModify(Long userId, CommentEntity entity, String comment) throws IllegalAccessException {
        if(!userId.equals(entity.getWriter().getId())) {
            throw new IllegalAccessException();
        }
        entity.modify(comment);
    }
}
