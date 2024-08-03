package mungmo.board.domain.board.facade;

import lombok.RequiredArgsConstructor;
import mungmo.board.com.response.exception.FileUploadException;
import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.external.BoardDto;
import mungmo.board.domain.board.external.PageDto;
import mungmo.board.domain.board.service.BoardService;
import mungmo.board.response.member.entity.MemberEntity;
import mungmo.board.response.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFacade {
    private final BoardService boardService;
    private final MemberService memberService;

    public List<BoardEntity> boardList(PageDto pageDTO) {
        return boardService.boardList(pageDTO);
    }

    public BoardEntity boardOne(Long id) {
        return boardService.boardOne(id);
    }

    public void boardResister(BoardDto dto, Long userId, MultipartFile fileInfo) throws FileUploadException {
        MemberEntity member = memberService.findEntityById(userId);
        boardService.boardResister(dto, member, fileInfo);
    }

    public void boardModify(Long userId, Long boardId, BoardDto dto) throws IllegalAccessException {
        BoardEntity entity = boardService.boardOne(boardId);
        boardService.boardModify(userId, entity, dto);
    }

    public void boardDelete(Long userId, long boardId) throws IllegalAccessException {
        BoardEntity entity = boardService.boardOne(boardId);
        boardService.boardDelete(userId, entity);
    }
}
