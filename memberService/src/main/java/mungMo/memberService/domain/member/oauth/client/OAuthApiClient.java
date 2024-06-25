package mungMo.memberService.domain.member.oauth.client;

import mungMo.memberService.domain.member.dto.SocialRoute;
import mungMo.memberService.domain.member.oauth.param.OAuthLoginParams;
import mungMo.memberService.domain.member.oauth.response.OAuthInfoResponse;

public interface OAuthApiClient {
    SocialRoute oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}