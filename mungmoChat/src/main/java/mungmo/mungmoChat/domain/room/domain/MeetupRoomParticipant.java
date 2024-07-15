package mungmo.mungmoChat.domain.room.domain;

import lombok.Builder;
import lombok.Getter;
import mungmo.mungmoChat.domain.room.dto.MeetupRoomParticipantDTO;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chatRoomParticipant")
public class MeetupRoomParticipant {
    @Id
    private Long id;

    @Indexed
    private Long chatRoomId;

    @Indexed
    private Long memberId;

    private LocalDateTime joinedAt;

    private boolean joinChat;

    public MeetupRoomParticipant() {
    }

    @Builder
    public MeetupRoomParticipant(Long memberId, Long chatRoomId, LocalDateTime joinedAt, boolean joinChat) {
        this.memberId = memberId;
        this.chatRoomId = chatRoomId;
        this.joinedAt = joinedAt;
        this.joinChat = joinChat;
    }

    public MeetupRoomParticipant(Long memberId, Long chatRoomId) {
        this.memberId = memberId;
        this.chatRoomId = chatRoomId;
        this.joinedAt = LocalDateTime.now();
        this.joinChat = false;
    }

    public static MeetupRoomParticipant fromJoinChat(MeetupRoomParticipantDTO participantDTO) {
        return MeetupRoomParticipant.builder()
                .memberId(participantDTO.getMemberId())
                .chatRoomId(participantDTO.getChatRoomId())
                .joinChat(false)
                .build();
    }

    public MeetupRoomParticipantDTO changeToDTO() {
        return MeetupRoomParticipantDTO.builder()
                .chatRoomId(chatRoomId)
                .memberId(memberId)
                .joinedAt(joinedAt)
                .build();
    }
}