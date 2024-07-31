package mungmo.adminService.admin.com.config;

import jakarta.servlet.Filter;
import mungmo.adminService.admin.com.filter.AuthorizationFilter;
import mungmo.adminService.admin.otherDomain.member.service.MemberService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    private final MemberService memberService;

    public FilterConfig(MemberService memberServiceClient) {
        this.memberService = memberServiceClient;
    }

    @Bean
    public FilterRegistrationBean<Filter> AuthorizationFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new AuthorizationFilter(memberService));
        bean.setOrder(1);
        bean.addUrlPatterns("/*");

        return bean;
    }
}
