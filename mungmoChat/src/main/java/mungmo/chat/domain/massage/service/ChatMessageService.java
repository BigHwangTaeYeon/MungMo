package mungmo.chat.domain.massage.service;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.massage.domain.ChatMessage;
import mungmo.chat.domain.massage.external.Message;
import mungmo.chat.domain.massage.infra.ChatMessageRepository;
import mungmo.chat.domain.massage.infra.repository.SpringDataJpaChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public void saveMessage(Message message) {
        chatMessageRepository.save(ChatMessage.from(message));
    }
}
