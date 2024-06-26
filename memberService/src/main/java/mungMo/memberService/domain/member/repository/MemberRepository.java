package mungMo.memberService.domain.member.repository;

import mungMo.memberService.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByNicknameAndOauthProvider(@Param("nickname") String nickname, @Param("oAuthProvider") String oAuthProvider);

    MemberEntity findByEmail(String email);

    MemberEntity findByNickname(@Param("nickname") String nickname);
}
