
package mungmo.chat.response.notification.service;

import lombok.RequiredArgsConstructor;
import mungmo.chat.response.notification.external.ChatNotificationClient;
import mungmo.chat.response.notification.infra.ChatNotificationRepository;
import mungmo.chat.response.notification.infra.repository.SpringDataJpaChatNotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatNotificationService {
    private final ChatNotificationRepository notificationClient;

    public void changeStatusReadTrue(Long roomId, Long userId) {
        notificationClient.changeStatusReadTrue(roomId, userId);
    }
}
