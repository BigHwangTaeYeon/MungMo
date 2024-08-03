package mungmo.chat.domain.room.entity;

import lombok.*;
import mungmo.chat.domain.room.external.MeetupRoomParticipantDto;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chat_room_participant")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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

    public void joinChatMeetup() {
        this.joinChat = true;
    }

    public void exitChatMeetup() {
        this.joinChat = false;
    }

    public void saveSeq(Long id) {
        this.id = id;
    }

    public static MeetupRoomParticipant fromJoinMeetup(MeetupRoomParticipantDto participantDTO) {
        return MeetupRoomParticipant.builder()
                .memberId(participantDTO.getMemberId())
                .chatRoomId(participantDTO.getChatRoomId())
                .joinChat(false)
                .build();
    }

    public MeetupRoomParticipantDto toDto() {
        return MeetupRoomParticipantDto.builder()
                .chatRoomId(chatRoomId)
                .memberId(memberId)
                .joinedAt(joinedAt)
                .build();
    }
}