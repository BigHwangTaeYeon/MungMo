package mungmo.board.domain.board.controller;

import lombok.RequiredArgsConstructor;
import mungmo.board.com.response.ResponseMessage;
import mungmo.board.com.util.arg.UserDto;
import mungmo.board.domain.board.external.CommentDto;
import mungmo.board.domain.board.facade.CommentFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentFacade commentFacade;

    @PostMapping
    public ResponseEntity<String> commentResister(UserDto userDto, @RequestBody CommentDto dto) {
        commentFacade.commentResister(dto.getComment(), dto.getBoardId(), userDto.getId());
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> commentModify(UserDto userDto, @PathVariable("id") long commentId, @RequestParam String comment) throws IllegalAccessException {
        commentFacade.commentModify(userDto.getId(), commentId, comment);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> commentDelete(UserDto userDto, @PathVariable("id") long commentId) throws IllegalAccessException {
        commentFacade.commentDelete(userDto.getId(), commentId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }
}
