package mungMo.member.domain.member.oauth.response;

import mungMo.member.domain.member.entity.SocialRoute;

public interface OAuthInfoResponse {
    String getEmail();
    String getGender();
    String getAgeRange();
    SocialRoute getOAuthProvider();
}