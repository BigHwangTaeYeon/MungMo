package mungmo.chat.domain.massage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mungmo.chat.domain.massage.external.Message;
import mungmo.chat.domain.massage.facade.ChatFacade;
import mungmo.chat.response.notification.external.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/socket")
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatFacade chatFacade;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/meetup")
    public void meetup(@RequestBody Message message){
        chatFacade.meetupMessageSend(message);
        chatFacade.meetupNotification(Notification.from(message));
        simpMessagingTemplate.convertAndSend("/sub/meetup/" + message.getChatRoomId(), message);
    }
}
