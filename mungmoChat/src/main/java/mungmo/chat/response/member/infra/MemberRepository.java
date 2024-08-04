package mungmo.chat.response.member.infra;

import mungmo.chat.response.member.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository {
    Optional<MemberEntity> findById(Long id);
}
