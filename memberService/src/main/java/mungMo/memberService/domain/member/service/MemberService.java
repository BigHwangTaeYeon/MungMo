package mungMo.memberService.domain.member.service;

import mungMo.memberService.com.util.Validation;
import mungMo.memberService.domain.member.entity.MemberEntity;
import mungMo.memberService.domain.member.oauth.client.RequestOAuthInfoService;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokens;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.param.OAuthLoginParams;
import mungMo.memberService.domain.member.oauth.response.OAuthInfoResponse;
import mungMo.memberService.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public MemberService(MemberRepository memberRepository, AuthTokensGenerator authTokensGenerator, RequestOAuthInfoService requestOAuthInfoService) {
        this.memberRepository = memberRepository;
        this.authTokensGenerator = authTokensGenerator;
        this.requestOAuthInfoService = requestOAuthInfoService;
    }

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    @Transactional(readOnly = true)
    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return Optional.ofNullable(memberRepository.findByEmail(oAuthInfoResponse.getEmail()))
                        .map(MemberEntity::getId)
                        .orElseGet(()->newMember(oAuthInfoResponse));
    }

    @Transactional(readOnly = false)
    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        MemberEntity member = MemberEntity.builder()
                .email(String.valueOf(UUID.randomUUID()))
//                .email(oAuthInfoResponse.getEmail())
                .nickname(Validation.Nickname(oAuthInfoResponse.getNickname()))
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        memberRepository.save(member);
        return memberRepository.findByEmail(member.getEmail()).getId();
//        return memberRepository.findByEmail(oAuthInfoResponse.getEmail()).getId();
    }

}
