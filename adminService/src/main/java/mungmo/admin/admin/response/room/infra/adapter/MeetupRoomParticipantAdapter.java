package mungmo.admin.admin.response.room.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.com.filter.ObjectConverter;
import mungmo.admin.admin.response.room.entity.MeetupRoomParticipant;
import mungmo.admin.admin.response.room.external.MeetupRoomParticipantClient;
import mungmo.admin.admin.response.room.infra.MeetupRoomParticipantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MeetupRoomParticipantAdapter implements MeetupRoomParticipantRepository {
    private final MeetupRoomParticipantClient participantServiceClient;

    @Override
    public List<MeetupRoomParticipant> chatNonParticipantsByFeignClient(Long roomId) {
        ResponseEntity<?> res = participantServiceClient.chatNonParticipants(roomId);
        return ObjectConverter.operatingList(res, MeetupRoomParticipant.class);
    }
}
