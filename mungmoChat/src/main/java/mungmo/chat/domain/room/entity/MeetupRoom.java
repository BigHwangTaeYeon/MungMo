package mungmo.chat.domain.room.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mungmo.chat.domain.room.external.MeetupRoomDto;
import mungmo.chat.response.member.entity.MemberEntity;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "chat_room")
@NoArgsConstructor
public class MeetupRoom {

    @jakarta.persistence.Id @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "meeting_date", nullable = false)
    private LocalDateTime meetingDate;

    // 참여자 수
    @Column(name = "participants_num", nullable = false)
    @ColumnDefault("30")
    private int participantsNum;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity owner;

    private MeetupRoom(MeetupRoomDto chatRoom, MemberEntity member) {
        this.title = chatRoom.getTitle();
        this.meetingDate = chatRoom.getMeetingDate();
        this.participantsNum = chatRoom.getParticipantsNum();
        this.createDate = LocalDateTime.now();
        this.owner = member;
    }

    public static MeetupRoom of(MeetupRoomDto chatRoom, MemberEntity member) {
        return new MeetupRoom(chatRoom, member);
    }

    public MeetupRoomDto toDto() {
        return MeetupRoomDto.builder()
                .roomId(id)
                .title(title)
                .participantsNum(participantsNum)
                .meetingDate(meetingDate)
                .owner(owner.toDto())
                .build();
    }
}
