package mungmo.chat.domain.room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mungmo.chat.com.config.ResponseMessage;
import mungmo.chat.com.util.Result;
import mungmo.chat.domain.room.external.MeetupRoomParticipantDto;
import mungmo.chat.domain.room.facade.MeetupRoomParticipantFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/meetup/participant")
@Slf4j
@RequiredArgsConstructor
public class MeetupRoomParticipantController {
    private final MeetupRoomParticipantFacade participantFacade;

    /**
     * 번개 방에 참여중인 회원 리스트
     * @param roomId
     * @return Long userId
     */
    @GetMapping("/{roomId}")
    public ResponseEntity<Result<List<MeetupRoomParticipantDto>>> participantListByMeetup(@PathVariable("roomId") Long roomId){
        return ResponseEntity.ok(new Result<>(
                participantFacade.participantListById(roomId)
        ));
    }

    /**
     * adminService
     * 채팅중이지 않은 사람
     * @return
     */
    @GetMapping("/out/{roomId}")
    public ResponseEntity<Result<List<MeetupRoomParticipantDto>>> chatNonParticipants(@PathVariable(name = "roomId") Long roomId){
        return ResponseEntity.ok(new Result<>(
                participantFacade.chatNonParticipants(roomId)
        ));
    }

    /**
     * 번개 참여
     * @param participantDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<String> joinMeetup(@RequestBody MeetupRoomParticipantDto participantDTO){
        participantFacade.joinMeetup(participantDTO);
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }

    /**
     * 번개 미참여
     * @param participantDTO
     * @return
     */
    @DeleteMapping
    public ResponseEntity<String> exitMeetup(@RequestBody MeetupRoomParticipantDto participantDTO){
        participantFacade.exitMeetup(participantDTO);
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }
}
