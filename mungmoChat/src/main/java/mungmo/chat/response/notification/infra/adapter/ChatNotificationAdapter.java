package mungmo.chat.response.notification.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.chat.response.notification.external.ChatNotificationClient;
import mungmo.chat.response.notification.infra.ChatNotificationRepository;
import mungmo.chat.response.notification.infra.repository.SpringDataJpaChatNotificationRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChatNotificationAdapter implements ChatNotificationRepository {
    private final SpringDataJpaChatNotificationRepository publicCodeRepository;
    private final ChatNotificationClient notificationClient;

    public void changeStatusReadTrue(Long roomId, Long userId) {
        notificationClient.changeStatusReadTrue(roomId, userId);
    }
}
