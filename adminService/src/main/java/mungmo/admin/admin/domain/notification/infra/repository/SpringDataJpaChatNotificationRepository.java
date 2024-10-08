package mungmo.admin.admin.domain.notification.infra.repository;

import mungmo.admin.admin.domain.notification.domain.ChatNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaChatNotificationRepository extends JpaRepository<ChatNotification, Long> {
    List<ChatNotification> findByRecipientIdAndChatRoomId(Long userId, Long roomNum);
}
