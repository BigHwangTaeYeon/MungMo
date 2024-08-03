package mungMo.member.domain.member.service;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.oauth.response.OAuthInfoResponse;
import mungMo.member.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberLoginService {
    private final MemberRepository memberRepository;


    @Transactional
    public Long login(MemberEntity member) {
        // 최근 접속 기록 남기기
        member.updateRecentDate();
        return member.getId();
    }

    @Transactional
    public MemberEntity findMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail());
    }

    @Transactional
    public MemberEntity newMember(OAuthInfoResponse oAuthInfoResponse) {
        MemberEntity member = MemberEntity.builder()
                .email(oAuthInfoResponse.getEmail())
                .gender(oAuthInfoResponse.getGender())
                .ageRange(oAuthInfoResponse.getAgeRange())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        memberRepository.save(member);

        return member;
    }
}
