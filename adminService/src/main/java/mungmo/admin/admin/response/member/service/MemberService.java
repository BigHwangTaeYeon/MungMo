package mungmo.admin.admin.response.member.service;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.response.member.entity.MemberEntity;
import mungmo.admin.admin.response.member.infra.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberEntity findMemberById(Long id) {
        return memberRepository.findMemberById(id);
    }

    @Transactional(readOnly = true)
    public MemberEntity getMemberByFeignClient(Long userId) {
        return memberRepository.getMemberByFeignClient(userId);
    }
}
