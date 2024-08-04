package mungmo.admin.admin.response.room.service;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.response.room.entity.MeetupRoomParticipant;
import mungmo.admin.admin.response.room.infra.MeetupRoomParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetupRoomParticipantService {
    private final MeetupRoomParticipantRepository participantRepository;

    public List<MeetupRoomParticipant> chatNonParticipantsByFeignClient(Long roomId) {
        return participantRepository.chatNonParticipantsByFeignClient(roomId);
    }
}
