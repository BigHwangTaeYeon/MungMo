package mungmo.chat.domain.massage.domain;

import jakarta.persistence.Id;
import lombok.*;
import mungmo.chat.domain.massage.external.Message;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chat_message")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Transient
    public static final String SEQUENCE_NAME = "chat_message_sequence";

    @Id
    private Long id;

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

    public void saveSeq(Long id) {
        this.id = id;
    }

    public static ChatMessage from(Message message) {
        return ChatMessage.builder()
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