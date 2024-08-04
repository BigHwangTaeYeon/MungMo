package mungMo.member.domain.member.infra;

import mungMo.member.domain.member.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository {
    MemberEntity findByEmail(String email);

    void save(MemberEntity member);

    Optional<MemberEntity> findById(Long id);
}
