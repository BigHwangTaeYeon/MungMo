package mungmo.mungmoChat.domain.massage.domain;

import jakarta.persistence.*;
import lombok.*;
import mungmo.mungmoChat.otherDomain.member.entity.MemberEntity;

@Entity
@Getter
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

    public ChatNotification() {
    }

    public ChatNotification(MemberEntity sender, Long recipientId, Long chatRoomId, String content) {
        this.sender = sender;
        this.recipientId = recipientId;
        this.chatRoomId = chatRoomId;
        this.content = content;
        this.hasRead = false;
    }

    public void changeHasReadToTrue() {
        this.hasRead = true;
    }
}