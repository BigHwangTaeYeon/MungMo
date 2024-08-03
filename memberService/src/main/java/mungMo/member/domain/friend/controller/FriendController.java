package mungMo.member.domain.friend.controller;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.config.ResponseMessage;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.com.util.Result;
import mungMo.member.com.util.arg.UserDto;
import mungMo.member.domain.friend.facade.FriendFacade;
import mungMo.member.domain.friend.external.FriendDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/friend")
@RequiredArgsConstructor
public class FriendController {
    private final FriendFacade friendFacade;

    /**
     * 친구 리스트
     * @return
     */
    @GetMapping
    public ResponseEntity<Result<List<FriendDto>>> listFriend(UserDto userDto) {
        return ResponseEntity.ok(new Result<>(
                    friendFacade.friendList(userDto.getId())
        ));
    }

    /**
     * 친구 신청
     * @param friendId
     * @return
     */
    @PostMapping("/{id}")
    public ResponseEntity<String> applyFriend(@PathVariable("id") Long friendId, UserDto userDto) throws PreconditionFailedException {
        friendFacade.apply(userDto.getId(), friendId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /**
     * 친구 수락
     * @param friendId
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> acceptFriend(@PathVariable("id") Long friendId, UserDto userDto) {
        friendFacade.accept(userDto.getId(), friendId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /**
     * 친구 삭제
     * @param friendId
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFriend(@PathVariable("id") Long friendId, UserDto userDto) {
        friendFacade.delete(userDto.getId(), friendId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }
}
