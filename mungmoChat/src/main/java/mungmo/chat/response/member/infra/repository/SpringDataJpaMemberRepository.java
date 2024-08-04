package mungmo.chat.response.member.infra.repository;

import mungmo.chat.response.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaMemberRepository extends JpaRepository<MemberEntity, Long> {
}
