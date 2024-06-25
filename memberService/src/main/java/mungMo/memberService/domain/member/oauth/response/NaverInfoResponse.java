package mungMo.memberService.domain.member.oauth.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mungMo.memberService.domain.member.dto.SocialRoute;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverInfoResponse implements OAuthInfoResponse {

    @JsonProperty("response")
    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {
        private String email;
        private String nickname;
    }

    @Override
    public String getEmail() {
        return response.email;
    }

    @Override
    public String getNickname() {
        return response.nickname;
    }

    @Override
    public SocialRoute getOAuthProvider() {
        return SocialRoute.NAVER;
    }
}