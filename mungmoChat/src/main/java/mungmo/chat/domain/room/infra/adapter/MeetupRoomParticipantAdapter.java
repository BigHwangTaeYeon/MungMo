package mungmo.chat.domain.room.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.room.entity.MeetupRoom;
import mungmo.chat.domain.room.entity.MeetupRoomParticipant;
import mungmo.chat.domain.room.infra.MeetupRoomParticipantRepository;
import mungmo.chat.domain.room.infra.MeetupRoomRepository;
import mungmo.chat.domain.room.infra.repository.SpringDataJpaMeetupRoomParticipantRepository;
import mungmo.chat.domain.room.infra.repository.SpringDataJpaMeetupRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MeetupRoomParticipantAdapter implements MeetupRoomParticipantRepository {
    private final SpringDataJpaMeetupRoomParticipantRepository participantRepository;

    @Override
    public List<MeetupRoomParticipant> findByMemberId(Long userId) {
        return participantRepository.findByMemberId(userId);
    }

    @Override
    public List<MeetupRoomParticipant> findByChatRoomId(Long chatRoomId) {
        return participantRepository.findByChatRoomId(chatRoomId);
    }

    @Override
    public List<MeetupRoomParticipant> findByChatRoomIdAndJoinChat(Long chatRoomId, boolean join) {
        return participantRepository.findByChatRoomIdAndJoinChat(chatRoomId, join);
    }

    @Override
    public MeetupRoomParticipant findByMemberIdAndChatRoomId(Long userId, Long chatRoomId) {
        return participantRepository.findByMemberIdAndChatRoomId(userId, chatRoomId);
    }

    @Override
    public void insert(MeetupRoomParticipant meetupRoomParticipant) {
        participantRepository.insert(meetupRoomParticipant);
    }

    @Override
    public void delete(MeetupRoomParticipant participant) {
        participantRepository.delete(participant);
    }

    @Override
    public void deleteByChatRoomId(Long roomId) {
        participantRepository.deleteByChatRoomId(roomId);
    }
}
