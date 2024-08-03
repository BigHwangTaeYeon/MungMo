

package mungmo.admin.admin.com.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.com.config.ResponseMessage;
import mungmo.admin.admin.response.member.entity.MemberEntity;
import mungmo.admin.admin.response.member.external.MemberDto;
import mungmo.admin.admin.response.member.entity.MemberAuthority;
import mungmo.admin.admin.response.member.service.MemberService;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {
    private final MemberService memberService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (authorized(req)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().write(ResponseMessage.FORBIDDEN.getMessage());
        }
    }

    private boolean authorized(HttpServletRequest req) {
        Long userId = Long.valueOf(req.getHeader("userId"));

        MemberEntity member = memberService.getMemberByFeignClient(userId);

        if(member.getMemberAuthority() == null) {
            throw new NullPointerException();
        }

        return member.getMemberAuthority().equals(MemberAuthority.ROLE_ADMIN);
    }
}
