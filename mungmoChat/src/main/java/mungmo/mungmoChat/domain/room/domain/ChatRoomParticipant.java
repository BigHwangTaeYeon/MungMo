package mungmo.mungmoChat.domain.room.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Getter
//@RedisHash(value = "chatRoomParticipant")
@Document(collection = "chatRoomParticipant")
public class ChatRoomParticipant {
    @Id
    private String id;
    @Indexed
    private Long memberId;
    @Indexed
    private Long chatRoomId;

    private LocalDateTime joinedAt;

    public ChatRoomParticipant() {
    }

    public ChatRoomParticipant(Long memberId, Long chatRoomId) {
        this.memberId = memberId;
        this.chatRoomId = chatRoomId;
        this.joinedAt = LocalDateTime.now();
    }
}