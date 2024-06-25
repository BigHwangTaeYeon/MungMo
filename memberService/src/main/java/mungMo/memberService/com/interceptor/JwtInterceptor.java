package mungMo.memberService.com.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    private final JwtTokenProvider jwtProvider; //JWT 유틸리티 객체 주입
    private final AuthTokensGenerator authTokensGenerator;

    public JwtInterceptor(JwtTokenProvider jwtProvider, AuthTokensGenerator authTokensGenerator) {
        this.jwtProvider = jwtProvider;
        this.authTokensGenerator = authTokensGenerator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = jwtProvider.getAccessToken(request);

        return !accessToken.isEmpty() && !jwtProvider.extractSubject(accessToken).isEmpty();

    }
}
