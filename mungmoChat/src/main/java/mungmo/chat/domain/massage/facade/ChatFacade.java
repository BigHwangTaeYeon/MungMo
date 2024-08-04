package mungmo.chat.domain.massage.facade;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.massage.external.Message;
import mungmo.chat.domain.massage.service.ChatService;
import mungmo.chat.response.notification.external.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatFacade {
    private final ChatService chatService;

    @Value("${kafka.topic.message.meetup}")
    private String meetupMessage;
    @Value("${kafka.topic.message.subgroup}")
    private String subgroupMessage;

    @Value("${kafka.topic.notification.meetup}")
    private String meetupNotification;
    @Value("${kafka.topic.notification.subgroup}")
    private String subgroupNotification;

    public void meetupMessageSend(Message message) {
        chatService.meetupMessageSend(meetupMessage, message);
    }

    public void meetupNotification(Notification notification) {
        chatService.meetupNotification(meetupNotification, notification);
    }
}
