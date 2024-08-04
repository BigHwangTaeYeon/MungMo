package mungmo.board.domain.board.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.board.domain.board.entity.BoardEntity;
import mungmo.board.domain.board.entity.Subject;
import mungmo.board.domain.board.infra.BoardRepository;
import mungmo.board.domain.board.infra.repository.SpringDataJpaBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardAdapter implements BoardRepository {
    private final SpringDataJpaBoardRepository boardRepository;

    @Override
    public Page<BoardEntity> findAllBySubject(Subject subject, Pageable pageable) {
        return boardRepository.findAllBySubject(subject, pageable);
    }

    @Override
    public Optional<BoardEntity> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public void save(BoardEntity entity) {
        boardRepository.save(entity);
    }

    @Override
    public void delete(BoardEntity entity) {
        boardRepository.delete(entity);
    }
}
