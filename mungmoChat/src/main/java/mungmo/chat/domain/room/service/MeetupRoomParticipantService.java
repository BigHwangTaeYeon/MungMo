package mungmo.chat.domain.room.service;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.room.entity.MeetupRoomParticipant;
import mungmo.chat.domain.room.external.MeetupRoomParticipantDto;
import mungmo.chat.domain.room.infra.MeetupRoomParticipantRepository;
import mungmo.chat.domain.room.infra.repository.SpringDataJpaMeetupRoomParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetupRoomParticipantService {
    private final MeetupRoomParticipantRepository participantRepository;

    @Transactional(readOnly = true)
    public List<MeetupRoomParticipant> participantListById(Long userId) {
        return participantRepository.findByMemberId(userId);
    }

    @Transactional(readOnly = true)
    public List<MeetupRoomParticipant> participantListByRoom(Long chatRoomId) {
        return participantRepository.findByChatRoomId(chatRoomId);
    }

    @Transactional(readOnly = true)
    public List<MeetupRoomParticipant> chatNonParticipants(Long chatRoomId) {
        return participantRepository.findByChatRoomIdAndJoinChat(chatRoomId, false);
    }

    @Transactional(readOnly = true)
    public MeetupRoomParticipant findByUserIdAndChatRoomId(Long userId, Long chatRoomId) {
        return participantRepository.findByMemberIdAndChatRoomId(userId, chatRoomId);
    }

    @Transactional
    public void joinChatMeetup(MeetupRoomParticipant participant) {
        participant.joinChatMeetup();
    }

    @Transactional
    public void exitChatMeetup(MeetupRoomParticipant participant) {
        participant.exitChatMeetup();
    }

    @Transactional
    public void joinMeetup(MeetupRoomParticipantDto participantDTO) {
        participantRepository.insert(MeetupRoomParticipant.fromJoinMeetup(participantDTO));
    }

    @Transactional
    public void exitMeetup(MeetupRoomParticipant participant) {
        participantRepository.delete(participant);
    }

    @Transactional
    public void deleteAllParticipant(Long roomId) {
        participantRepository.deleteByChatRoomId(roomId);
    }

}
