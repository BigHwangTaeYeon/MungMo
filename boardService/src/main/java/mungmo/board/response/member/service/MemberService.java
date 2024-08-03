package mungmo.board.response.member.service;

import lombok.RequiredArgsConstructor;
import mungmo.board.response.member.entity.MemberEntity;
import mungmo.board.response.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberEntity findEntityById(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }
}
