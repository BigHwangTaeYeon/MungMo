package mungmo.admin.admin.domain.notification.external;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Serializable {
    private Long chatRoomId;
    private Long recipientId;
    private Long sendId;
    private String content;
    private String senderNick;
    private LocalDateTime createdDate;
}