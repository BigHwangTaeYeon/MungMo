package mungMo.memberService.domain.member.api;

import jakarta.servlet.http.HttpServletRequest;
import mungMo.memberService.com.annotation.LoginCheckEssential;
import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.com.util.JwtUtils;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import mungMo.memberService.domain.member.service.MemberTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
public class MemberTypeController {
    private final JwtUtils jwtUtils;
    private final MemberTypeService memberTypeService;

    public MemberTypeController(JwtUtils jwtUtils, MemberTypeService memberTypeService) {
        this.jwtUtils = jwtUtils;
        this.memberTypeService = memberTypeService;
    }

    /**
     * 유저 모든 타입 가져오기
     * @param request
     * @return
     */
    @LoginCheckEssential
    @PatchMapping("/userTypeList")
    public ResponseEntity<?> userTypeList(HttpServletRequest request) {
        return ResponseEntity.ok(
                new Result<>(memberTypeService.userTypeList(jwtUtils.getIdFromRequest(request)))
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
        memberTypeService.userTypeResister(jwtUtils.getIdFromRequest(request), code);
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
        memberTypeService.dogTypeResister(jwtUtils.getIdFromRequest(request), code);
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }

    public static class Result<T> {
        private final T data;
        public Result(T data) {
            this.data = data;
        }
    }

}