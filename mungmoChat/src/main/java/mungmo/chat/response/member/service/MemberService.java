package mungmo.chat.response.member.service;

import lombok.RequiredArgsConstructor;
import mungmo.chat.response.member.entity.MemberEntity;
import mungmo.chat.response.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberEntity findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }
}
