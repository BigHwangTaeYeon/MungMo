package mungmo.admin.admin.response.room.service;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.com.filter.ObjectConverter;
import mungmo.admin.admin.response.room.entity.MeetupRoomParticipant;
import mungmo.admin.admin.response.room.external.MeetupRoomParticipantClient;
import mungmo.admin.admin.response.room.repository.MeetupRoomParticipantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetupRoomParticipantService {
    private final MeetupRoomParticipantRepository participantRepository;
    private final MeetupRoomParticipantClient participantServiceClient;

    public List<MeetupRoomParticipant> chatNonParticipantsByFeignClient(Long roomId) {
        ResponseEntity<?> res = participantServiceClient.chatNonParticipants(roomId);
        return ObjectConverter.operatingList(res, MeetupRoomParticipant.class);
    }
}
