package mungmo.board.domain.board.infra.repository;

import mungmo.board.domain.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaCommentRepository extends JpaRepository<CommentEntity, Long> {

}
