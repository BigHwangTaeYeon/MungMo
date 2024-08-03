package mungMo.member.domain.member.facade;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.exception.ValidationException;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.oauth.client.RequestOAuthInfoService;
import mungMo.member.domain.member.oauth.jwt.AuthTokens;
import mungMo.member.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.member.domain.member.oauth.param.OAuthLoginParams;
import mungMo.member.domain.member.oauth.response.OAuthInfoResponse;
import mungMo.member.domain.member.service.MemberLoginService;
import mungMo.member.domain.member.service.MemberTypeService;
import mungMo.member.domain.town.service.TownService;
import mungMo.member.response.publiccode.entity.PublicCodeEntity;
import mungMo.member.response.publiccode.entity.PublicType;
import mungMo.member.response.publiccode.repository.PublicCodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static mungMo.member.response.publiccode.entity.PublicType.DGTP;
import static mungMo.member.response.publiccode.entity.PublicType.MBTP;

@Service
@RequiredArgsConstructor
public class MemberLoginFacade {
    private final MemberLoginService memberLoginService;

    private final PublicCodeRepository publicCodeRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;
    private final TownService townService;
    private final MemberTypeService memberTypeService;

    @Transactional
    public AuthTokens login(OAuthLoginParams params) {
        // 소셜에서 유저 정보 조회
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);

        // 유저 정보 있으면 반환 없으면 새로 등록 후 반환
        MemberEntity member = Optional.ofNullable(memberLoginService.findMember(oAuthInfoResponse))
                .orElseGet(() -> newMember(oAuthInfoResponse));

        // 접속 기록 저장 및 id 반환
        Long memberId = memberLoginService.login(member);

        return authTokensGenerator.generate(memberId);
    }

    @Transactional
    public MemberEntity newMember(OAuthInfoResponse oAuthInfoResponse) {

        MemberEntity member = memberLoginService.newMember(oAuthInfoResponse);
        townService.saveMemberEntity(member);

        List<PublicCodeEntity> publicMBTPEntity = publicCodeRepository.findByCodeTypeAndUseYN(PublicType.str(MBTP), true);
        List<PublicCodeEntity> publicDGTPEntity = publicCodeRepository.findByCodeTypeAndUseYN(PublicType.str(DGTP), true);
        memberTypeService.save(publicMBTPEntity, member);
        memberTypeService.save(publicDGTPEntity, member);

        return member;
    }

}
