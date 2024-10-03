package mungmo.admin.admin.domain.notification.vo;

import lombok.*;
import mungmo.admin.admin.domain.notification.domain.ChatNotification;
import mungmo.admin.admin.response.member.entity.MemberEntity;
import mungmo.admin.admin.response.room.external.MeetupRoomParticipantDto;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatNotificationVo implements Serializable {
    private Long chatNotificationId;
    private Long chatRoomId;
    private Long recipientId;
    private Long sendId;
    private String content;
    private String senderNick;
    private Boolean hasRead;
    private LocalDateTime createdDate;

    public void changeStatusReadTrue() {
        this.hasRead = true;
    }

    private ChatNotificationVo(MemberEntity sender, MeetupRoomParticipantDto participantDTO, ChatNotificationVo notification) {
        this.sendId = sender.getId();
        this.recipientId = participantDTO.getMemberId();
        this.chatRoomId = participantDTO.getChatRoomId();
        this.content = notification.getContent();
        this.hasRead = false;
    }

    public static ChatNotificationVo of(MemberEntity sender, MeetupRoomParticipantDto participantDTO, ChatNotificationVo notification) {
        return new ChatNotificationVo(sender, participantDTO, notification);
    }
}
