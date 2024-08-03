package mungmo.board.domain.board.service;

import lombok.RequiredArgsConstructor;
import mungmo.board.com.response.exception.FileUploadException;
import mungmo.board.com.response.exception.NotFoundException;
import mungmo.board.com.util.Upload;
import mungmo.board.domain.board.external.BoardDto;
import mungmo.board.domain.board.external.DogBoardDto;
import mungmo.board.domain.board.external.PageDto;
import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.repository.BoardRepository;
import mungmo.board.response.member.entity.MemberEntity;
import mungmo.board.response.member.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Value("${api.upload.dir.board}")
    private String uploadDir;

    public List<BoardEntity> boardList(PageDto pageDTO) {
        return boardRepository.findAllBySubject(pageDTO.getSubject(), PageRequest.of(pageDTO.getPage(), pageDTO.getSize(), Sort.by(ASC, "createDate")))
                .getContent();
    }

    public BoardEntity boardOne(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public void boardResister(BoardDto dto, MemberEntity member, MultipartFile multipartFile) throws FileUploadException {
        if(ObjectUtils.isEmpty(multipartFile)) {
            boardRepository.save(BoardEntity.of(dto, member));
        } else {
            Map<String, String> file = new Upload(uploadDir, multipartFile).uploadImage();
            boardRepository.save(BoardEntity.of(dto.setFile(file),member));
        }
    }

    public void boardModify(Long userId, BoardEntity entity, BoardDto dto) throws IllegalAccessException {
        if (!userId.equals(entity.getWriter().getId())) {
            throw new IllegalAccessException();
        }
        entity.modify(dto);
    }

    public void boardDelete(Long userId, BoardEntity entity) throws IllegalAccessException {
        if (!userId.equals(entity.getWriter().getId())) {
            throw new IllegalAccessException();
        }
        boardRepository.delete(entity);
    }
}
