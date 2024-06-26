package mungMo.memberService.domain.member.town.controller;


import jakarta.servlet.http.HttpServletRequest;
import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import mungMo.memberService.domain.member.town.service.TownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TownController {
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtProvider;
    private final TownService townService;

    public TownController(AuthTokensGenerator authTokensGenerator, JwtTokenProvider jwtProvider, TownService townService) {
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
        this.townService = townService;
    }

    @PatchMapping("/registerTown")
    public ResponseEntity<?> loginKakao(HttpServletRequest request,@RequestParam String area) {
        townService.register(area, authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }
}
