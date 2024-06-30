package mungMo.memberService.domain.member.api;

import jakarta.servlet.http.HttpServletRequest;
import mungMo.memberService.com.annotation.LoginCheckEssential;
import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.com.exception.PreconditionFailedException;
import mungMo.memberService.com.exception.UnauthorizedException;
import mungMo.memberService.com.exception.ValidationException;
import mungMo.memberService.com.util.JwtUtils;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import mungMo.memberService.domain.member.service.MemberApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1")
public class MemberApiController {
    private final MemberApiService memberApiService;
    private final AuthTokensGenerator authTokensGenerator;

    public MemberApiController(MemberApiService memberApiService, AuthTokensGenerator authTokensGenerator) {
        this.memberApiService = memberApiService;
        this.authTokensGenerator = authTokensGenerator;
    }

    /*
     * 토큰으로 정보 가져오기
     */
    @LoginCheckEssential
    @GetMapping("/memberInfo")
    public ResponseEntity<?> myInfo(HttpServletRequest request) throws PreconditionFailedException {
        return ResponseEntity.ok(
                new Result<>(memberApiService.infoById(authTokensGenerator.extractMemberIdToHeader(request)))
        );
    }

    /*
     * id로 정보 가져오기
     */
    @LoginCheckEssential
    @GetMapping("/memberInfo/{id}")
    public ResponseEntity<?> yourInfo(@PathVariable("id") Long id) throws PreconditionFailedException {
        return ResponseEntity.ok(
                new Result<>(memberApiService.infoById(id))
        );
    }

    /**
     *
     * @param request
     * @return dogName, id
     * @throws UnauthorizedException
     */
    @LoginCheckEssential
    @GetMapping("/dogName")
    public ResponseEntity<?> dogName(HttpServletRequest request) throws UnauthorizedException {
        return ResponseEntity.ok(
                new Result<>(memberApiService.dogName(authTokensGenerator.extractMemberIdToHeader(request)))
        );
    }

    /**
     * 강아지 정보 - 좋아요 등록
     * @param request
     * @param like
     * @return
     */
    @LoginCheckEssential
    @PostMapping("/dogLike")
    public ResponseEntity<?> dogLike(HttpServletRequest request, String like) {
        memberApiService.dogLike(authTokensGenerator.extractMemberIdToHeader(request), like);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /*
     * 닉네임 등록
     */
    @LoginCheckEssential
    @PatchMapping("/registerNickname")
    public ResponseEntity<?> regiNickname(HttpServletRequest request, @RequestParam("nickName") String nickName) throws ValidationException {
        memberApiService.registerNickname(nickName, authTokensGenerator.extractMemberIdToHeader(request));
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /*
     * 강아지 사진 업로드
     */
    @LoginCheckEssential
    @PatchMapping("/updateDogImg")
    public ResponseEntity<?> updateDogImg(HttpServletRequest request, MultipartFile file){
        memberApiService.updateDogImg(authTokensGenerator.extractMemberIdToHeader(request), file);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    public static class Result<T> {
        private final T data;
        public Result(T data) {
            this.data = data;
        }
    }

}