package mungmo.admin.admin.domain.notification.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.domain.notification.domain.ChatNotification;
import mungmo.admin.admin.domain.notification.infra.NotificationRepository;
import mungmo.admin.admin.domain.notification.infra.repository.SpringDataJpaChatNotificationRepository;
import mungmo.admin.admin.domain.notification.vo.ChatNotificationVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class NotificationAdapter implements NotificationRepository {
    private final SpringDataJpaChatNotificationRepository notificationRepository;

    @Override
    public List<ChatNotificationVo> findByRecipientIdAndChatRoomId(Long userId, Long roomNum) {
        return notificationRepository.findByRecipientIdAndChatRoomId(userId, roomNum)
                .stream()
                .map(ChatNotification::toVo)
                .collect(Collectors.toList());
    }

    @Override
    public void save(ChatNotificationVo of) {

    }
}
