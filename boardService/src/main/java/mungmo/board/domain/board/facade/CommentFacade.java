package mungmo.board.domain.board.facade;

import lombok.RequiredArgsConstructor;
import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.entity.CommentEntity;
import mungmo.board.domain.board.service.BoardService;
import mungmo.board.domain.board.service.CommentService;
import mungmo.board.response.member.entity.MemberEntity;
import mungmo.board.response.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentFacade {
    private final CommentService commentService;
    private final MemberService memberService;
    private final BoardService boardService;

    public void commentResister(String comment, Long boardId, Long userId) {
        BoardEntity boardEntity = boardService.boardOne(boardId);
        MemberEntity memberEntity = memberService.findEntityById(userId);

        commentService.commentResister(comment, boardEntity, memberEntity);
    }

    public void commentDelete(Long userId, Long commentId) throws IllegalAccessException {
        CommentEntity comment = commentService.comment(commentId);
        commentService.commentDelete(userId, comment);
    }

    public void commentModify(Long userId, Long commentId, String comment) throws IllegalAccessException {
        CommentEntity commentEntity = commentService.comment(commentId);
        commentService.commentModify(userId, commentEntity, comment);
    }
}
