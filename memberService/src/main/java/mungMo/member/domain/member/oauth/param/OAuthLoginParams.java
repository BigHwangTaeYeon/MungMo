package mungMo.member.domain.member.oauth.param;

import mungMo.member.domain.member.entity.SocialRoute;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
    SocialRoute oAuthProvider();
    MultiValueMap<String, String> makeBody();
}
