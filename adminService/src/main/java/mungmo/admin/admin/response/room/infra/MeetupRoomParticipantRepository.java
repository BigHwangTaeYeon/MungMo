package mungmo.admin.admin.response.room.infra;

import mungmo.admin.admin.response.room.entity.MeetupRoomParticipant;

import java.util.List;

public interface MeetupRoomParticipantRepository {
    List<MeetupRoomParticipant> chatNonParticipantsByFeignClient(Long roomId);
}
