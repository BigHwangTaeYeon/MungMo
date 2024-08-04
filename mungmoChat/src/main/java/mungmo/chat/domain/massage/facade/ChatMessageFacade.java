package mungmo.chat.domain.massage.facade;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.massage.external.Message;
import mungmo.chat.domain.massage.service.ChatMessageService;
import mungmo.chat.domain.massage.service.ChatService;
import mungmo.chat.response.notification.external.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageFacade {
    private final ChatMessageService chatMessageService;

    public void saveMessage(Message message) {
        chatMessageService.saveMessage(message);
    }
}
