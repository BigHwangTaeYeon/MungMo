package mungMo.memberService.domain.member.service;

import mungMo.memberService.com.exception.ValidationException;
import mungMo.memberService.com.util.Validation;
import mungMo.memberService.domain.member.entity.MemberEntity;
import mungMo.memberService.domain.member.oauth.client.RequestOAuthInfoService;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokens;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.param.OAuthLoginParams;
import mungMo.memberService.domain.member.oauth.response.OAuthInfoResponse;
import mungMo.memberService.domain.member.repository.MemberRepository;
import mungMo.memberService.domain.member.town.repository.TownRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static mungMo.memberService.domain.member.town.entity.TownEntity.firstCreateInstance;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TownRepository townRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public MemberService(MemberRepository memberRepository, TownRepository townRepository,
                         AuthTokensGenerator authTokensGenerator, RequestOAuthInfoService requestOAuthInfoService) {
        this.memberRepository = memberRepository;
        this.townRepository = townRepository;
        this.authTokensGenerator = authTokensGenerator;
        this.requestOAuthInfoService = requestOAuthInfoService;
    }

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);

        // 최근 접속 기록 남기기
        memberRepository.findById(memberId).ifPresent(
                member -> {
                    member.updateRecentDate();
                    memberRepository.save(member);
                }
        );

        return authTokensGenerator.generate(memberId);
    }

    @Transactional(readOnly = true)
    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return Optional.ofNullable(memberRepository.findByEmail(oAuthInfoResponse.getEmail()))
                        .map(MemberEntity::getId)
                        .orElseGet(()-> {
                            try {
                                return newMember(oAuthInfoResponse);
                            } catch (ValidationException e) {
                                throw new RuntimeException(e);
                            }
                        });
    }

    @Transactional(readOnly = false)
    private Long newMember(OAuthInfoResponse oAuthInfoResponse) throws ValidationException {
        MemberEntity member = MemberEntity.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(Validation.nicknameConfirm(oAuthInfoResponse.getGender()))
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        memberRepository.save(member);
        townRepository.save(firstCreateInstance(member.getId()));
        return memberRepository.findByEmail(member.getEmail()).getId();
    }

}
