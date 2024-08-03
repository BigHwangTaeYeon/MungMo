package mungmo.chat.response.notification.external;

import lombok.*;
import mungmo.chat.domain.massage.external.Message;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification implements Serializable {

    private Long chatRoomId;
    private Long recipientId;
    private Long sendId;
    private String content;
    private String senderNick;
    private LocalDateTime createdDate;

    public static Notification from(Message message) {
        return Notification.builder()
                .chatRoomId(message.getChatRoomId())
                .sendId(message.getSenderId())
                .content(message.getContent())
                .createdDate(LocalDateTime.now())
                .build();
    }
}