package mungmo.chat.domain.massage.service;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.massage.external.Message;
import mungmo.chat.response.notification.external.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final KafkaTemplate<String, Message> messageKafkaTemplate;
    private final KafkaTemplate<String, Notification> notificationKafkaTemplate;

    public void meetupMessageSend(String meetupMessage, Message message) {
        messageKafkaTemplate.send(meetupMessage, message);
    }

    public void meetupNotification(String meetupNotification, Notification notification) {
        notificationKafkaTemplate.send(meetupNotification, notification);
    }
}
