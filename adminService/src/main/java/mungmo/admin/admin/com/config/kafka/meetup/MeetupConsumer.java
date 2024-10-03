package mungmo.admin.admin.com.config.kafka.meetup;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.domain.notification.vo.ChatNotificationVo;
import mungmo.admin.admin.domain.notification.facade.NotificationFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetupConsumer {
    private final NotificationFacade notificationFacade;

    @KafkaListener(topics = "${kafka.topic.notification.meetup}", groupId = "${kafka.consumer.id}", containerFactory = "kafkaNotificationContainerFactory")
    public void consumeNotification(ChatNotificationVo notification) {
        // 알림 저장
        notificationFacade.saveNotification(notification);
        // 알림 전송
        notificationFacade.sendPushNotification(notification);
    }
}
