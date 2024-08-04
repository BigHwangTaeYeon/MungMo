package mungmo.admin.admin.domain.notification.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.domain.notification.domain.ChatNotification;
import mungmo.admin.admin.domain.notification.infra.NotificationRepository;
import mungmo.admin.admin.domain.notification.infra.repository.SpringDataJpaChatNotificationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class NotificationAdapter implements NotificationRepository {
    private final SpringDataJpaChatNotificationRepository notificationRepository;

    @Override
    public List<ChatNotification> findByRecipientIdAndChatRoomId(Long userId, Long roomNum) {
        return notificationRepository.findByRecipientIdAndChatRoomId(userId, roomNum);
    }
}
