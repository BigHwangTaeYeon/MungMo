package mungmo.admin.admin.domain.notification.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mungmo.admin.admin.domain.notification.vo.ChatNotificationVo;
import mungmo.admin.admin.response.member.entity.MemberEntity;
import mungmo.admin.admin.response.room.external.MeetupRoomParticipantDto;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ChatNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_notification_id", nullable = false)
    private Long id;

    // 상대방의 정보가 변경되면 반영해서 보여줄 것이므로 연관 관계를 맺도록 한다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private MemberEntity sender;

    @Column(name = "recipient_id", nullable = false)
    private Long recipientId;

    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "has_read", nullable = false)
    private Boolean hasRead;

    private ChatNotification(MemberEntity sender, MeetupRoomParticipantDto participantDTO, ChatNotificationVo notification) {
        this.sender = sender;
        this.recipientId = participantDTO.getMemberId();
        this.chatRoomId = participantDTO.getChatRoomId();
        this.content = notification.getContent();
        this.hasRead = false;
    }

    public static ChatNotification of(MemberEntity sender, MeetupRoomParticipantDto participantDTO, ChatNotificationVo notification) {
        return new ChatNotification(sender, participantDTO, notification);
    }

    public ChatNotificationVo toVo() {
        return ChatNotificationVo.builder()
                .chatNotificationId(id)
                .chatRoomId(chatRoomId)
                .recipientId(recipientId)
                .sendId(sender.getId())
                .senderNick(sender.getDogName())
                .content(content)
                .hasRead(hasRead)
                .build();
    }
}