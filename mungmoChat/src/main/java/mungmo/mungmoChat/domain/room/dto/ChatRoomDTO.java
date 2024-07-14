package mungmo.mungmoChat.domain.room.dto;

import lombok.Getter;
import lombok.Setter;
import mungmo.mungmoChat.otherDomain.member.dto.MemberDTO;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomDTO {
    private Long roomId;

    private String title;

    private LocalDateTime meetingDate;

    private int participantsNum;

    private MemberDTO owner;
}
