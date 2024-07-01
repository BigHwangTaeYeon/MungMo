package mungMo.memberService.domain.member.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import mungMo.memberService.com.annotation.LoginCheckEssential;
import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.service.MemberTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class MemberTypeController {
    private final AuthTokensGenerator authTokensGenerator;
    private final MemberTypeService memberTypeService;

    public MemberTypeController(AuthTokensGenerator authTokensGenerator, MemberTypeService memberTypeService) {
        this.authTokensGenerator = authTokensGenerator;
        this.memberTypeService = memberTypeService;
    }

    /**
     * 유저 모든 타입 가져오기
     * @param request
     * @return
     */
    @LoginCheckEssential
    @GetMapping("/userTypeList")
    public ResponseEntity<?> userTypeList(HttpServletRequest request) {
        return ResponseEntity.ok(
                new Result<>(memberTypeService.userTypeList(authTokensGenerator.extractMemberIdToHeader(request)))
        );
    }

    /**
     * 유저 타입 저장
     * @param request type(유저), code(내향, 외향)
     * @param code
     * @return
     */
    @LoginCheckEssential
    @PatchMapping("/userTypeResister")
    public ResponseEntity<?> userTypeResister(HttpServletRequest request, @RequestParam List<Integer> code) {
        memberTypeService.userTypeResister(authTokensGenerator.extractMemberIdToHeader(request), code);
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }

    /**
     * 강아지 타입 저장
     * @param request type(강아지), code(내향, 외향)
     * @param code
     * @return
     */
    @LoginCheckEssential
    @PatchMapping("/dogTypeResister")
    public ResponseEntity<?> dogTypeResister(HttpServletRequest request, @RequestParam List<Integer> code) {
        memberTypeService.dogTypeResister(authTokensGenerator.extractMemberIdToHeader(request), code);
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }

    @Getter
    public static class Result<T> {
        private final T data;
        public Result(T data) {
            this.data = data;
        }
    }

}