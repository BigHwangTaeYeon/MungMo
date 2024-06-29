package mungMo.memberService.domain.member.api;

import jakarta.servlet.http.HttpServletRequest;
import mungMo.memberService.com.annotation.LoginCheckEssential;
import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.com.exception.PreconditionFailedException;
import mungMo.memberService.com.exception.UnauthorizedException;
import mungMo.memberService.com.exception.ValidationException;
import mungMo.memberService.com.util.JwtUtils;
import mungMo.memberService.domain.member.service.MemberApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
public class MemberApiController {
    private final MemberApiService memberApiService;
    private final JwtUtils jwtUtils;

    public MemberApiController(MemberApiService memberApiService, JwtUtils jwtUtils) {
        this.memberApiService = memberApiService;
        this.jwtUtils = jwtUtils;
    }

    /*
     * 토큰으로 정보 가져오기
     */
    @LoginCheckEssential
    @GetMapping("/memberInfo")
    public ResponseEntity<?> myInfo(HttpServletRequest request) throws PreconditionFailedException {
        return ResponseEntity.ok(
                new Result<>(memberApiService.infoById(jwtUtils.getIdFromRequest(request)))
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
                new Result<>(memberApiService.dogName(jwtUtils.getIdFromRequest(request)))
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
        memberApiService.dogLike(jwtUtils.getIdFromRequest(request), like);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /*
     * 닉네임 등록
     */
    @LoginCheckEssential
    @PatchMapping("/registerNickname")
    public ResponseEntity<?> regiNickname(HttpServletRequest request, @RequestParam String nickName) throws ValidationException {
        memberApiService.registerNickname(nickName, jwtUtils.getIdFromRequest(request));
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /*
     * 강아지 사진 업로드
     */
    @LoginCheckEssential
    @PatchMapping("/updateDogImg")
    public ResponseEntity<?> updateDogImg(HttpServletRequest request, MultipartFile file){
        memberApiService.updateDogImg(jwtUtils.getIdFromRequest(request), file);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    public static class Result<T> {
        private final T data;
        public Result(T data) {
            this.data = data;
        }
    }

}