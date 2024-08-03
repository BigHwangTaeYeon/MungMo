package mungmo.board.domain.board.controller;

import lombok.RequiredArgsConstructor;
import mungmo.board.com.response.ResponseMessage;
import mungmo.board.com.response.exception.FileUploadException;
import mungmo.board.com.util.Result;
import mungmo.board.com.util.arg.UserDto;
import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.external.BoardDto;
import mungmo.board.domain.board.external.DogBoardDto;
import mungmo.board.domain.board.external.PageDto;
import mungmo.board.domain.board.facade.BoardFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardFacade boardFacade;

    @GetMapping
    public ResponseEntity<Result<List<DogBoardDto>>> boardList(@RequestBody PageDto pageDTO) {
        return ResponseEntity.ok(new Result<>(
                boardFacade.boardList(pageDTO)
                        .stream()
                        .map(BoardEntity::toDogBoardDto)
                        .collect(Collectors.toList())
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<DogBoardDto>> board(@PathVariable("id") long id) {
        return ResponseEntity.ok(new Result<>(boardFacade.boardOne(id).toDogBoardDto()));
    }

    @PostMapping
    public ResponseEntity<String> boardResister(UserDto userDto, BoardDto dto, MultipartFile file) throws FileUploadException {
        boardFacade.boardResister(dto, userDto.getId(), file);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> boardModify(UserDto userDto, @PathVariable("id") long boardId, BoardDto dto) throws IllegalAccessException {
        boardFacade.boardModify(userDto.getId(), boardId, dto);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> boardDelete(UserDto userDto, @PathVariable("id") long boardId) throws IllegalAccessException {
        boardFacade.boardDelete(userDto.getId(), boardId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }
}
