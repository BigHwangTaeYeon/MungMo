package mungmo.chat.domain.room.external;

import lombok.*;
import mungmo.chat.response.member.external.MemberDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetupRoomDto {
    private Long roomId;

    private String title;

    private LocalDateTime meetingDate;

    private int participantsNum;

    private MemberDto owner;
}
