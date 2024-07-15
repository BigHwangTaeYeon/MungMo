package mungmo.mungmoChat.domain.massage.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@Builder
public class Notification implements Serializable {

    private Long chatRoomId;
    private Long recipientId;
    private Long sendId;
    private String content;
    private String senderNick;
    private LocalDateTime createdDate;
//    private NotificationType notificationType;

    public Notification() {
    }

    public Notification(Long chatRoomId, Long recipientId, String senderNick, LocalDateTime createdDate, String content) {
        this.chatRoomId = chatRoomId;
        this.recipientId = recipientId;
        this.senderNick = senderNick;
        this.createdDate = createdDate;
        this.content = content;
    }

    public Notification(Message message) {
        Notification.builder()
                .chatRoomId(message.getChatRoomId())
                .sendId(message.getSenderId())
                .content(message.getContent())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public static Notification from(Message message) {
        return new Notification(message);
    }
}