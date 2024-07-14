package mungmo.mungmoChat.domain.massage.controller;

import lombok.extern.slf4j.Slf4j;
import mungmo.mungmoChat.domain.massage.dto.Message;
import mungmo.mungmoChat.domain.test.chat.dto.test;
import org.springframework.kafka.core.KafkaTemplate;
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

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final KafkaTemplate<String, Message> chatKafkaTemplate;

    private final static String CHAT_MASSAGE = "chat_massage";
    private final static String CHAT_NOTIFICATION = "chat_notification";

    public ChatController(SimpMessagingTemplate simpMessagingTemplate, KafkaTemplate<String, Message> chatKafkaTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatKafkaTemplate = chatKafkaTemplate;
    }

    @PostMapping(value = "/test")
    public void test(@RequestBody Message message){
        System.out.println("message.toString() = " + message.toString());

        chatKafkaTemplate.send(CHAT_MASSAGE, message);
    }

    // /pub/chat/enter 에 메세지가 오면 동작
    @MessageMapping(value = "/chat/enter")
    public void enter(test message){ // 채팅방 입장
//        message.setMessage(message.getId() + "님이 채팅방에 참여하였습니다.");
        simpMessagingTemplate.convertAndSend("/sub/chat/" + message.getRoomId(), message);
    }

    // /pub/chat/message 에 메세지가 오면 동작
    @MessageMapping(value = "/chat/message")
    public void message(test message){



//        ChatResponseDto savedMessage = chatService.saveMessage(message);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + 1, message);
//        simpMessagingTemplate.convertAndSend("/sub/chat/" + savedMessage.getRoomId(), savedMessage);
    }

}
