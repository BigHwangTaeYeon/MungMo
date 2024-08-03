package mungmo.chat.com.config.kafka.meetup;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.massage.external.Message;
import mungmo.chat.domain.massage.service.ChatMessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetupConsumer {
    private final ChatMessageService messageService;

    @KafkaListener(topics = "${kafka.topic.message.meetup}", groupId = "${kafka.consumer.id}", containerFactory = "kafkaChatContainerFactory")
    public void consumeMessage(Message message) {
        // 데이터베이스에 저장
        messageService.saveMessage(message);
    }
}
