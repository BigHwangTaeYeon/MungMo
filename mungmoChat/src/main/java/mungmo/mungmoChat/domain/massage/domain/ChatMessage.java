package mungmo.mungmoChat.domain.massage.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import mungmo.mungmoChat.domain.massage.dto.Message;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@Document(collection = "chat_message")
public class ChatMessage {
    @Id
    private String id;

    @Indexed
    private Long chatRoomId;

    private Long senderId;

    private String content;

    private LocalDateTime createdAt;

    private int readCount;

    // 소모임, 번개
    private ChatType chatType; // 채팅 타입 필드 추가('TEXT', 'IMAGE')

    private String imageName;

    private String imageUrl;

    public ChatMessage() {
    }

    public ChatMessage(Message message) {
        ChatMessage.builder()
                .chatRoomId(message.getChatRoomId())
                .senderId(message.getSenderId())
                .content(message.getContent())
                .createdAt(LocalDateTime.now())
                .readCount(message.getReadCount())
                .chatType(message.getChatType())
                .imageName(message.getImageName())
                .imageUrl(message.getImageUrl())
                .build();
    }
}