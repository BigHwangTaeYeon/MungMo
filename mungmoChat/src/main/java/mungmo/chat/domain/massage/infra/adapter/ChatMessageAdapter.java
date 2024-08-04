package mungmo.chat.domain.massage.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.massage.domain.ChatMessage;
import mungmo.chat.domain.massage.infra.ChatMessageRepository;
import mungmo.chat.domain.massage.infra.repository.SpringDataJpaChatMessageRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChatMessageAdapter implements ChatMessageRepository {
    private final SpringDataJpaChatMessageRepository chatMessageRepository;

    @Override
    public void save(ChatMessage from) {
        chatMessageRepository.save(from);
    }
}
