package mungMo.memberService.domain.member.oauth.response;

import mungMo.memberService.domain.member.dto.SocialRoute;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    SocialRoute getOAuthProvider();
}