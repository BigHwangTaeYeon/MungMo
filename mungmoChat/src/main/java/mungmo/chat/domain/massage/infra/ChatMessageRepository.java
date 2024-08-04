package mungmo.chat.domain.massage.infra;

import mungmo.chat.domain.massage.domain.ChatMessage;

public interface ChatMessageRepository {
    void save(ChatMessage from);
}
