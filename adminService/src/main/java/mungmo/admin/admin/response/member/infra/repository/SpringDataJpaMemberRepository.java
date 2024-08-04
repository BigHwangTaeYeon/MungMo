package mungmo.admin.admin.response.member.infra.repository;

import mungmo.admin.admin.response.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaMemberRepository extends JpaRepository<MemberEntity, Long> {
}
