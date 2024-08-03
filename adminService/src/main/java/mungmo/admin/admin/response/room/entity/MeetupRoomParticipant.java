package mungmo.admin.admin.response.room.entity;

import jakarta.persistence.Id;
import lombok.*;
import mungmo.admin.admin.response.room.external.MeetupRoomParticipantDto;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chat_room_participant")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MeetupRoomParticipant {

    @Transient
    public static final String SEQUENCE_NAME = "chat_room_participant_sequence";

    @Id
    private Long id;

    @Indexed
    private Long chatRoomId;

    @Indexed
    private Long memberId;

    private LocalDateTime joinedAt;

    private boolean joinChat;

    public void saveSeq(Long id) {
        this.id = id;
    }

    public MeetupRoomParticipantDto toDto() {
        return MeetupRoomParticipantDto.builder()
                .chatRoomId(chatRoomId)
                .memberId(memberId)
                .joinedAt(joinedAt)
                .build();
    }
}