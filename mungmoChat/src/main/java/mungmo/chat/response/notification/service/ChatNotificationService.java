
package mungmo.chat.response.notification.service;

import lombok.RequiredArgsConstructor;
import mungmo.chat.response.notification.external.ChatNotificationClient;
import mungmo.chat.response.notification.repository.ChatNotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class ChatNotificationService {
    private final ChatNotificationRepository chatNotificationRepository;
    private final ChatNotificationClient notificationClient;

    public void changeStatusReadTrue(Long roomId, Long userId) {
        notificationClient.changeStatusReadTrue(roomId, userId);
    }
}
