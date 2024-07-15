package mungmo.mungmoChat.com.config.kafka.meetup;

import mungmo.mungmoChat.domain.massage.dto.Message;
import mungmo.mungmoChat.domain.massage.dto.Notification;
import mungmo.mungmoChat.domain.massage.service.ChatMessageService;
import mungmo.mungmoChat.domain.massage.service.ChatNotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MeetupConsumer {
    private final ChatMessageService messageService;
    private final ChatNotificationService chatNotificationService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public MeetupConsumer(ChatMessageService messageService, ChatNotificationService chatNotificationService, SimpMessagingTemplate simpMessagingTemplate) {
        this.messageService = messageService;
        this.chatNotificationService = chatNotificationService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @KafkaListener(topics = "chat_meetup_message", groupId = "${kafka.consumer.id}", containerFactory = "kafkaChatContainerFactory")
    public void consumeMessage(Message message) {
        // 데이터베이스에 저장
        messageService.saveMessage(message);
        // 메시지 출력
        simpMessagingTemplate.convertAndSend("/sub/meetup/" + message.getChatRoomId(), message);
    }

    @KafkaListener(topics = "chat_meetup_notification", groupId = "${kafka.consumer.id}", containerFactory = "kafkaNotificationContainerFactory")
    public void consumeNotification(Notification notification) {
        // 알림 저장
        chatNotificationService.

        // 알림 전송
        notificationService.sendNotification(notification);

        // 알림 메시지 출력
        System.out.println("Received notification: " + notification.getContent());
    }
}
