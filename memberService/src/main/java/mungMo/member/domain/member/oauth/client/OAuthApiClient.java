package mungMo.member.domain.member.oauth.client;

import mungMo.member.domain.member.entity.SocialRoute;
import mungMo.member.domain.member.oauth.param.OAuthLoginParams;
import mungMo.member.domain.member.oauth.response.OAuthInfoResponse;

public interface OAuthApiClient {
    SocialRoute oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}