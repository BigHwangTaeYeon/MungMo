package mungMo.memberService.com.config;

import mungMo.memberService.com.interceptor.JwtInterceptor;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenProvider jwtProvider; //JWT 유틸리티 객체 주입
    private final AuthTokensGenerator authTokensGenerator;

    public WebConfig(JwtTokenProvider jwtProvider, AuthTokensGenerator authTokensGenerator) {
        this.jwtProvider = jwtProvider;
        this.authTokensGenerator = authTokensGenerator;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(jwtProvider, authTokensGenerator))
                .addPathPatterns("/api/myTaste");
//                .addPathPatterns("*");
        System.out.println("WebConfig addInterceptors");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://118.67.132.171")
//                .allowedOrigins("http://dev.utteok.com")
                .allowedOrigins("*")
                .allowedMethods("GET","POST")
                .allowCredentials(false);
        System.out.println("WebConfig addCorsMappings");
    }
}
