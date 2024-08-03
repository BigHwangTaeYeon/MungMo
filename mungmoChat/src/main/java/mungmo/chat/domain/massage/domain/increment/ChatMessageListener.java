package mungmo.chat.domain.massage.domain.increment;

import lombok.RequiredArgsConstructor;
import mungmo.chat.com.config.mongo.increment.SequenceGeneratorService;
import mungmo.chat.domain.massage.domain.ChatMessage;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

@Component
@RequiredArgsConstructor
public class ChatMessageListener extends AbstractMongoEventListener<ChatMessage> {

    private final SequenceGeneratorService generatorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ChatMessage> event) {
        event.getSource().saveSeq(generatorService.generateSequence(ChatMessage.SEQUENCE_NAME));
    }
}