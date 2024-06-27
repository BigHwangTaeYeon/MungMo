package mungMo.memberService.domain.friend.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import mungMo.memberService.com.annotation.LoginCheckEssential;
import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.domain.friend.service.FriendService;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class FriendController {
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtProvider;
    private final FriendService friendService;

    public FriendController(AuthTokensGenerator authTokensGenerator, JwtTokenProvider jwtProvider, FriendService friendService) {
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
        this.friendService = friendService;
    }

    @LoginCheckEssential
    @PostMapping("/applyFriend/{id}")
    public ResponseEntity<?> applyFriend(HttpServletRequest request, @PathVariable("id") Long friendId) {
        friendService.apply(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)), friendId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @LoginCheckEssential
    @PatchMapping("/acceptFriend/{id}")
    public ResponseEntity<?> acceptFriend(HttpServletRequest request, @PathVariable("id") Long friendId) {
        friendService.accept(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)), friendId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @LoginCheckEssential
    @DeleteMapping("/deleteFriend/{id}")
    public ResponseEntity<?> deleteFriend(HttpServletRequest request, @PathVariable("id") Long friendId) {
        friendService.delete(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)), friendId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @LoginCheckEssential
    @GetMapping("/listFriend")
    public ResponseEntity<?> listFriend(HttpServletRequest request) {
        return ResponseEntity.ok(new Result(
                friendService.friendList(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)))
        ));
    }

    @Getter
    private static class Result<T> {
        private final T data;

        public Result(T data) {
            this.data = data;
        }
    }
}
