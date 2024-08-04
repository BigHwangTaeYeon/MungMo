package mungmo.admin.admin.domain.notification.infra;

import mungmo.admin.admin.domain.notification.domain.ChatNotification;

import java.util.List;

public interface NotificationRepository {
    List<ChatNotification> findByRecipientIdAndChatRoomId(Long userId, Long roomNum);
}
