package mungmo.chat.domain.massage.infra.repository;

import mungmo.chat.domain.massage.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaChatMessageRepository extends MongoRepository<ChatMessage, Long> {
}
