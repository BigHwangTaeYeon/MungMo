package mungMo.boardService.domain.otherService.member.repository;

import mungMo.boardService.domain.otherService.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
