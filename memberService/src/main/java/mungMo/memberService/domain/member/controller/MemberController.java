package mungMo.memberService.domain.member.controller;

import mungMo.memberService.domain.member.oauth.jwt.AuthTokens;
import mungMo.memberService.domain.member.oauth.param.KakaoLoginParams;
import mungMo.memberService.domain.member.oauth.param.NaverLoginParams;
import mungMo.memberService.domain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class MemberController {

    private final MemberService oAuthLoginService;

    public MemberController(MemberService oAuthLoginService) {
        this.oAuthLoginService = oAuthLoginService;
    }

    @GetMapping("/getTokenToKakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestParam(value = "code", required = false) String code) {
        KakaoLoginParams params = new KakaoLoginParams(code);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
//    https://kauth.kakao.com/oauth/authorize?client_id=1b02a8c5d5f529866e3bb44855645b62&redirect_uri=http://localhost:8000/member-service/v1/getTokenToKakao&response_type=code


    @GetMapping("/getTokenToNaver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestParam(value = "code", required = false) String code) {
        NaverLoginParams params = new NaverLoginParams(code);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

}
