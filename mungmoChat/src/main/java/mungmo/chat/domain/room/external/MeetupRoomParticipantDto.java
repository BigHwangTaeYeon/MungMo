package mungmo.chat.domain.room.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class MeetupRoomParticipantDto {
    private Long memberId;

    private Long chatRoomId;

    private LocalDateTime joinedAt;
}
