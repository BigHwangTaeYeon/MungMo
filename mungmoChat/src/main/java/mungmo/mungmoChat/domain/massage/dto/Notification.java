package mungmo.mungmoChat.domain.massage.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Notification implements Serializable {

    private Long chatRoomId;
    private Long recipientId;
    private String senderNick;
    private String senderProfileImage;
    private LocalDateTime createdDate;
    private String content;
//    private NotificationType notificationType;

    public Notification() {
    }

    public Notification(Long chatRoomId, Long recipientId, String senderNick, String senderProfileImage, LocalDateTime createdDate, String content) {
        this.chatRoomId = chatRoomId;
        this.recipientId = recipientId;
        this.senderNick = senderNick;
        this.senderProfileImage = senderProfileImage;
        this.createdDate = createdDate;
        this.content = content;
    }

    public static Notification createReadCountUpdateNotification(Long chatRoomId, Long otherMemberId) {
        return new Notification(
                chatRoomId,
                otherMemberId,
                null,
                null,
                LocalDateTime.now(),
                null
//                NotificationType.READ_COUNT_UPDATE
        );
    }
}