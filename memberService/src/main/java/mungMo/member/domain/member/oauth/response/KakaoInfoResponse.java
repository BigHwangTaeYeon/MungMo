package mungMo.member.domain.member.oauth.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mungMo.member.domain.member.entity.SocialRoute;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoInfoResponse implements OAuthInfoResponse {

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoAccount {
//        private KakaoProfile profile;
        private String gender;
        private String email;
        private String age_range;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoProfile {
//        private KakaoProfile profile;
    }

    @Override
    public String getEmail() {
        return kakaoAccount.email;
    }

    @Override
    public String getGender() {
        return kakaoAccount.gender;
//        return kakaoAccount.profile.gender;
    }

    @Override
    public String getAgeRange() {
        return kakaoAccount.age_range;
    }

    @Override
    public SocialRoute getOAuthProvider() {
        return SocialRoute.KAKAO;
    }
}