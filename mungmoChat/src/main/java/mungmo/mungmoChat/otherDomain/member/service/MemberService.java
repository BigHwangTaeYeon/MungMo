package mungmo.mungmoChat.otherDomain.member.service;

import mungmo.mungmoChat.otherDomain.member.dto.MemberDTO;
import mungmo.mungmoChat.otherDomain.member.entity.MemberEntity;
import mungmo.mungmoChat.otherDomain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
    }
}
