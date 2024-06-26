package mungMo.memberService.domain.member.api;

import jakarta.servlet.http.HttpServletRequest;
import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.com.exception.ValidationException;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import mungMo.memberService.domain.member.service.MemberApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
public class MemberApiController {
    private final MemberApiService memberApiService;
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtProvider;

    public MemberApiController(MemberApiService memberApiService, AuthTokensGenerator authTokensGenerator, JwtTokenProvider jwtProvider) {
        this.memberApiService = memberApiService;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
    }

    /*
     * 닉네임 사용 여부 체크 - true 사용중 false 미사용
     * websocket 으로 실시간 데이터 확인
     */
    @GetMapping("/checkNickname")
    public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname) throws ValidationException {
        return ResponseEntity.ok(memberApiService.checkIfEnabledNickName(nickname));
    }

    /*
     * 닉네임 등록
     */
    @PatchMapping("/registerNickname")
    public ResponseEntity<?> regiNickname(HttpServletRequest request, @RequestParam String nickName) throws ValidationException {
        memberApiService.registerNickname(nickName, getId(request));
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /*
     * 강아지 사진 업로드
     */
    @PatchMapping("/updateDogImg")
    public ResponseEntity<?> updateDogImg(HttpServletRequest request, MultipartFile file){
        memberApiService.updateDogImg(getId(request), file);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    private long getId(HttpServletRequest request) {
        return authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request));
    }

}