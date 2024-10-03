package mungmo.admin.admin.domain.notification.infra;

import mungmo.admin.admin.domain.notification.vo.ChatNotificationVo;

import java.util.List;

public interface NotificationRepository {
    List<ChatNotificationVo> findByRecipientIdAndChatRoomId(Long userId, Long roomNum);

    void save(ChatNotificationVo of);
}
