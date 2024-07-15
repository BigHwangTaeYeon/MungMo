package mungmo.mungmoChat.domain.room.service;

import mungmo.mungmoChat.domain.room.domain.MeetupRoomParticipant;
import mungmo.mungmoChat.domain.room.dto.MeetupRoomParticipantDTO;
import mungmo.mungmoChat.domain.room.repository.MeetupRoomParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetupRoomParticipantService {
    private final MeetupRoomParticipantRepository participantRepository;

    public MeetupRoomParticipantService(MeetupRoomParticipantRepository chatRoomParticipantRepository) {
        this.participantRepository = chatRoomParticipantRepository;
    }

    public List<MeetupRoomParticipantDTO> participantListById(long userId) {
        return participantRepository.findByMemberId(userId)
                .stream()
                .map(MeetupRoomParticipant::changeToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<MeetupRoomParticipantDTO> participantListByRoom(Long chatRoomId) {
        return participantRepository.findByChatRoomId(chatRoomId)
                .stream()
                .map(MeetupRoomParticipant::changeToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void joinMeetup(MeetupRoomParticipantDTO participantDTO) {
        participantRepository.insert(new MeetupRoomParticipant(participantDTO.getMemberId(), participantDTO.getChatRoomId()));
    }

    public void exitMeetup(MeetupRoomParticipantDTO participantDTO) {
        MeetupRoomParticipant participant = participantRepository.findByMemberIdAndChatRoomId(participantDTO.getMemberId(), participantDTO.getChatRoomId());
        participantRepository.delete(participant);
    }

    public void deleteAllParticipant(Long roomId) {
        participantRepository.deleteByChatRoomId(roomId);
    }
}
