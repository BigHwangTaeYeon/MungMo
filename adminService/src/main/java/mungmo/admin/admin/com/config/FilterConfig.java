package mungmo.admin.admin.com.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.com.filter.AuthorizationFilter;
import mungmo.admin.admin.com.util.arg.UserDto;
import mungmo.admin.admin.response.member.service.MemberService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final MemberService memberService;

    @Bean
    public FilterRegistrationBean<Filter> AuthorizationFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new AuthorizationFilter(memberService));
        bean.setOrder(1);
        bean.addUrlPatterns("/*");

        return bean;
    }
}
