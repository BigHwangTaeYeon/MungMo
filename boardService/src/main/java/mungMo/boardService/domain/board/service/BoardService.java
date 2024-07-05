package mungMo.boardService.domain.board.service;

import mungMo.boardService.com.response.exception.FileUploadException;
import mungMo.boardService.com.response.exception.NotFoundException;
import mungMo.boardService.com.util.Upload;
import mungMo.boardService.domain.board.dto.BoardDTO;
import mungMo.boardService.domain.board.dto.DogBoardDTO;
import mungMo.boardService.domain.board.dto.PageDTO;
import mungMo.boardService.domain.board.embede.FileInfo;
import mungMo.boardService.domain.board.entity.BoardEntity;
import mungMo.boardService.domain.board.repository.BoardRepository;
import mungMo.boardService.domain.otherService.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Value("${api.upload.dir.board}")
    private String uploadDir;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public List<DogBoardDTO> boardList(PageDTO pageDTO) {
        return boardRepository.findAllBySubject(pageDTO.getSubject(), PageRequest.of(pageDTO.getPage(), pageDTO.getSize(), Sort.by(ASC, "createDate")))
                .getContent()
                .stream()
                .map(
                        entity -> DogBoardDTO.builder()
                                .board(entity.changeToDTO())
                                .dogInfo(entity.changToDogInfoDTO())
                                .build()
                )
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public DogBoardDTO boardOne(long id) {
        return boardRepository.findById(id)
                .map(
                        entity -> DogBoardDTO.builder()
                                .board(entity.changeToDTO())
                                .dogInfo(entity.changToDogInfoDTO())
                                .build()
                )
                .orElseThrow(NotFoundException::new);
    }

    public void boardResister(BoardDTO dto, Long userId, MultipartFile file) {

        try {
            boardRepository.save(BoardEntity.of(
                    dto.setFile(new Upload(uploadDir, file).uploadImage(), "img"),
                    memberRepository.findById(userId).orElseThrow(NotFoundException::new))
            );
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }

    }

    public void boardDelete(Long userId, long boardId) {
        boardRepository.findById(boardId)
                .filter(entity -> Objects.equals(entity.getWriter().getId(), userId))
                .ifPresent(boardRepository::delete);
    }

    public void boardModify(Long userId, long boardId, BoardDTO dto) {
        boardRepository.findById(boardId)
                .filter(entity -> Objects.equals(entity.getWriter().getId(), userId))
                .ifPresent(board -> board.modify(dto));
    }
}
