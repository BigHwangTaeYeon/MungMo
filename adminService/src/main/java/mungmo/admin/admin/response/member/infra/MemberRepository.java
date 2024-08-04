package mungmo.admin.admin.response.member.infra;

import mungmo.admin.admin.response.member.entity.MemberEntity;

public interface MemberRepository {
    MemberEntity findMemberById(Long id);

    MemberEntity getMemberByFeignClient(Long userId);
}
