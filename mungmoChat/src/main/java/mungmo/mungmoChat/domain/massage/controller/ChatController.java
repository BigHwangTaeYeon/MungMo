package mungmo.mungmoChat.domain.massage.controller;

import lombok.extern.slf4j.Slf4j;
import mungmo.mungmoChat.domain.massage.dto.Message;
import mungmo.mungmoChat.domain.massage.dto.Notification;
import mungmo.mungmoChat.domain.massage.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/meetup")
    public void test(@RequestBody Message message){
        System.out.println("message.toString() = " + message.toString());

        chatService.meetupMessageSend(message);
        chatService.meetupNotification(Notification.from(message));
    }
}
