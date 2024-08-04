package mungmo.board.response.member.infra;

import mungmo.board.response.member.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository {
    Optional<MemberEntity> findById(Long id);
}
