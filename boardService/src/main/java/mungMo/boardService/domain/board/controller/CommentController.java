package mungMo.boardService.domain.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import mungMo.boardService.com.response.ResponseMessage;
import mungMo.boardService.domain.board.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("commentResister/id")
    public ResponseEntity<?> commentResister(HttpServletRequest request, String comment, Long boardId) {
        commentService.commentResister(comment, boardId, Long.valueOf(request.getHeader("userId")));
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /**
     * DELETE
     */
    @DeleteMapping("commentDelete/id/{id}")
    public ResponseEntity<?> commentDelete(HttpServletRequest request, @PathVariable("id") long commentId) {
        commentService.commentDelete(Long.valueOf(request.getHeader("userId")), commentId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /**
     * MODIFY
     */
    @PatchMapping("commentModify/id/{id}")
    public ResponseEntity<?> commentModify(HttpServletRequest request, @PathVariable("id") long commentId, String comment) {
        commentService.commentModify(Long.valueOf(request.getHeader("userId")), commentId, comment);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

}
