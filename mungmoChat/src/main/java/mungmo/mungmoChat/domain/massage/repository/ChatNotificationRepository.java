package mungmo.mungmoChat.domain.massage.repository;

import mungmo.mungmoChat.domain.massage.domain.ChatNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatNotificationRepository extends JpaRepository<ChatNotification, Long> {
}
