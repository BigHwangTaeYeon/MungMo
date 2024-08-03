package mungmo.chat.response.notification.repository;

import mungmo.chat.response.notification.entity.ChatNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatNotificationRepository extends JpaRepository<ChatNotification, Long> {
}
