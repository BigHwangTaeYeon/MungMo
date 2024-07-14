package mungmo.mungmoChat.domain.room.controller;

import lombok.extern.slf4j.Slf4j;
import mungmo.mungmoChat.domain.room.dto.ChatRoomDTO;
import mungmo.mungmoChat.domain.room.service.ChatRoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
public class RoomController {

    private final ChatRoomService chatRoomService;

    public RoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping(value = "/createMeetupChatRoom")
    public void createMeetupChatRoom(@RequestBody ChatRoomDTO chatRoom){
        chatRoomService.createMeetup(chatRoom);
    }

}
