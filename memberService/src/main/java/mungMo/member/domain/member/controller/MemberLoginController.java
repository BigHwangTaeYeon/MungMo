package mungMo.member.domain.member.controller;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.config.ResponseMessage;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.com.exception.ValidationException;
import mungMo.member.com.util.Result;
import mungMo.member.com.util.arg.UserDto;
import mungMo.member.domain.member.external.MemberDto;
import mungMo.member.domain.member.facade.MemberLoginFacade;
import mungMo.member.domain.member.oauth.jwt.AuthTokens;
import mungMo.member.domain.member.oauth.param.KakaoLoginParams;
import mungMo.member.domain.member.oauth.param.NaverLoginParams;
import mungMo.member.domain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberLoginController {
    private final MemberLoginFacade oAuthLoginService;

    @GetMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestParam(value = "code", required = false) String code) {
        return ResponseEntity.ok(oAuthLoginService.login(new KakaoLoginParams(code)));
    }

    @GetMapping("/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestParam(value = "code", required = false) String code) {
        return ResponseEntity.ok(oAuthLoginService.login(new NaverLoginParams(code)));
    }
}
