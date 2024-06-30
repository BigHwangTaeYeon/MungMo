package mungMo.memberService.domain.member.service;

import mungMo.memberService.com.exception.ValidationException;
import mungMo.memberService.domain.member.entity.MemberEntity;
import mungMo.memberService.domain.member.oauth.client.RequestOAuthInfoService;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokens;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.param.OAuthLoginParams;
import mungMo.memberService.domain.member.oauth.response.OAuthInfoResponse;
import mungMo.memberService.domain.member.repository.MemberRepository;
import mungMo.memberService.domain.member.repository.MemberTypeRepository;
import mungMo.memberService.domain.otherService.publicCode.entity.PublicCodeEntity;
import mungMo.memberService.domain.otherService.publicCode.repository.PublicCodeRepository;
import mungMo.memberService.domain.town.entity.TownEntity;
import mungMo.memberService.domain.town.repository.TownRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TownRepository townRepository;
    private final MemberTypeRepository memberTypeRepository;
    private final PublicCodeRepository publicCodeRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public MemberService(MemberRepository memberRepository, TownRepository townRepository, MemberTypeRepository memberTypeRepository, PublicCodeRepository publicCodeRepository, AuthTokensGenerator authTokensGenerator, RequestOAuthInfoService requestOAuthInfoService) {
        this.memberRepository = memberRepository;
        this.townRepository = townRepository;
        this.memberTypeRepository = memberTypeRepository;
        this.publicCodeRepository = publicCodeRepository;
        this.authTokensGenerator = authTokensGenerator;
        this.requestOAuthInfoService = requestOAuthInfoService;
    }

    @Transactional
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

    @Transactional
    private Long newMember(OAuthInfoResponse oAuthInfoResponse) throws ValidationException {
        TownEntity town = new TownEntity();
        townRepository.save(town);

        MemberEntity member = MemberEntity.builder()
                .email(oAuthInfoResponse.getEmail())
                .gender(oAuthInfoResponse.getGender())
                .ageRange(oAuthInfoResponse.getAgeRange())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .town(town)
                .build();
        memberRepository.save(member);

        for (PublicCodeEntity PCEntity : publicCodeRepository.findByCodeTypeAndUseYN("MBTP", true)) {
            memberTypeRepository.save(PCEntity.changeToMemberType(member));
        }
        for (PublicCodeEntity PCEntity : publicCodeRepository.findByCodeTypeAndUseYN("DGTP", true)) {
            memberTypeRepository.save(PCEntity.changeToMemberType(member));
        }

        return memberRepository.findByEmail(member.getEmail()).getId();
    }

}
