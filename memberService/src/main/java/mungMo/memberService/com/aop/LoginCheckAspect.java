package mungMo.memberService.com.aop;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import mungMo.memberService.com.exception.LoginException;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import mungMo.memberService.com.config.ResponseMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
@Slf4j
public class LoginCheckAspect {
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;

    public LoginCheckAspect(JwtTokenProvider jwtProvider, AuthTokensGenerator authTokensGenerator) {
        this.jwtProvider = jwtProvider;
        this.authTokensGenerator = authTokensGenerator;
    }

    @Before("@annotation(mungMo.memberService.com.annotation.LoginCheckEssential)")
    public void LoginCheckEssential(JoinPoint jp) throws Throwable{
        Arrays.stream(jp.getArgs())
                .filter(argument -> argument instanceof HttpServletRequest)
                .filter(request -> !((HttpServletRequest) request).getHeader("Authorization").isEmpty())
                .filter(result -> !jwtProvider.extractSubject(jwtProvider.getAccessToken(((HttpServletRequest) result))).isEmpty())
                .findFirst()
                .orElseThrow(() -> new LoginException(ResponseMessage.valueOfCode("Unauthorized").getMessage()));
    }

    private String validateId(HttpServletRequest request) {
        return jwtProvider.extractSubject(jwtProvider.getAccessToken(request));
    }
}
