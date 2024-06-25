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
//    https://kauth.kakao.com/oauth/authorize?client_id=8889b0f6c16199d16f1efb01ff1ec808&redirect_uri=http://localhost:8000/member-service/v1/getTokenToKakao&response_type=code


    @GetMapping("/getTokenToNaver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestParam(value = "code", required = false) String code) {
        NaverLoginParams params = new NaverLoginParams(code);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @GetMapping("test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("test");
    }

}
