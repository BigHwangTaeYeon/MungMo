package mungMo.member.domain.member.infra;

import mungMo.member.domain.member.entity.MemberTypeEntity;

import java.util.LinkedList;
import java.util.List;

public interface MemberTypeRepository {
    List<MemberTypeEntity> findByMemberId(Long memberId);

    LinkedList<MemberTypeEntity> findByMemberIdAndPublicCodeCodeType(Long id, String type);

    void save(MemberTypeEntity memberType);
}
