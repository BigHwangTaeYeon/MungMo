package mungmo.mungmoChat.domain.room.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import mungmo.mungmoChat.com.util.GetDate;
import mungmo.mungmoChat.domain.room.dto.ChatRoomDTO;
import mungmo.mungmoChat.otherDomain.member.dto.MemberDTO;
import mungmo.mungmoChat.otherDomain.member.entity.MemberEntity;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "chat_room")
public class ChatRoom {

    @jakarta.persistence.Id @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "meeting_date", nullable = false)
    private LocalDateTime meetingDate;

    @Column(name = "participants_num", nullable = false)
    @ColumnDefault("30")
    private int participantsNum;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity owner;

    public ChatRoom() {
    }

    private ChatRoom(ChatRoomDTO chatRoom, MemberEntity member) {
        ChatRoom.builder()
                .title(chatRoom.getTitle())
                .meetingDate(chatRoom.getMeetingDate())
                .participantsNum(chatRoom.getParticipantsNum())
                .createDate(GetDate.pareLocalDataTime("yyyyMMddHHmmss"))
                .owner(member)
                .build();
    }

    public static ChatRoom of(ChatRoomDTO chatRoom, MemberEntity member) {
        return new ChatRoom(chatRoom, member);
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sender_id", nullable = false)
//    private MemberEntity sender;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "recipient_id", nullable = false)
//    private MemberEntity recipient;


//    public ChatRoom(Long id, MemberEntity sender, MemberEntity recipient) {
//        this.id = id;
//        this.sender = sender;
//        this.recipient = recipient;
//    }
//
//    public static ChatRoom createNewChatRoom(MemberEntity sender, MemberEntity recipient) {
//        return ChatRoom.builder()
//                .sender(sender)
//                .recipient(recipient)
//                .build();
//    }
}
