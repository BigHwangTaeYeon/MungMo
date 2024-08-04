package mungmo.chat.response.notification.infra;

public interface ChatNotificationRepository {
    void changeStatusReadTrue(Long roomId, Long userId);
}
