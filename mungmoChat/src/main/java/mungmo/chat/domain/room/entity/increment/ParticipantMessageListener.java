package mungmo.chat.domain.room.entity.increment;

import lombok.RequiredArgsConstructor;
import mungmo.chat.com.config.mongo.increment.SequenceGeneratorService;
import mungmo.chat.domain.room.entity.MeetupRoomParticipant;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantMessageListener extends AbstractMongoEventListener<MeetupRoomParticipant> {
    private final SequenceGeneratorService generatorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<MeetupRoomParticipant> event) {
        event.getSource().saveSeq(generatorService.generateSequence(MeetupRoomParticipant.SEQUENCE_NAME));
    }
}