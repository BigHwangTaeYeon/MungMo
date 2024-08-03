package mungmo.chat.domain.room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mungmo.chat.com.config.ResponseMessage;
import mungmo.chat.com.exception.NotFoundException;
import mungmo.chat.com.util.Result;
import mungmo.chat.com.util.arg.UserDto;
import mungmo.chat.domain.room.external.MeetupRoomDto;
import mungmo.chat.domain.room.facade.MeetupRoomFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/meetup")
@Slf4j
@RequiredArgsConstructor
public class MeetupRoomController {
    private final MeetupRoomFacade meetupRoomFacade;

    /**
     * 방 리스트
     * @return
     */
    @GetMapping
    public ResponseEntity<Result<List<MeetupRoomDto>>> meetupList(){
        return ResponseEntity.ok(new Result<>(
                meetupRoomFacade.meetupList()
        ));
    }

    /**
     * 방 정보
     * @param roomId
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result<MeetupRoomDto>> meetupList(@PathVariable Long roomId) {
        return ResponseEntity.ok(new Result<>(meetupRoomFacade.meetupInfo(roomId)));
    }

    /**
     * 유저가 참여중인 방 리스트
     * @return Long roomId
     */
    @GetMapping("/user")
    public ResponseEntity<Result<List<MeetupRoomDto>>> participantList(UserDto userDto){
        return ResponseEntity.ok(new Result<>(
                meetupRoomFacade.meetupListByUser(userDto.getId())
        ));
    }

    /**
     * 방 생성
     * @param chatRoom
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createMeetup(UserDto userDto, @RequestBody MeetupRoomDto chatRoom){
        meetupRoomFacade.createMeetup(chatRoom, userDto.getId());
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }

    /**
     * 방 파괴
     * @param chatRoom
     * @return
     */
    @DeleteMapping
    public ResponseEntity<String> deleteMeetup(@RequestBody MeetupRoomDto chatRoom){
        meetupRoomFacade.deleteMeetup(chatRoom);
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }
}
