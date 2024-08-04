package mungmo.chat.domain.room.infra;

import mungmo.chat.domain.room.entity.MeetupRoom;
import mungmo.chat.domain.room.entity.MeetupRoomParticipant;

import java.util.List;
import java.util.Optional;

public interface MeetupRoomParticipantRepository {
    List<MeetupRoomParticipant> findByMemberId(Long userId);

    List<MeetupRoomParticipant> findByChatRoomId(Long chatRoomId);

    List<MeetupRoomParticipant> findByChatRoomIdAndJoinChat(Long chatRoomId, boolean join);

    MeetupRoomParticipant findByMemberIdAndChatRoomId(Long userId, Long chatRoomId);

    void insert(MeetupRoomParticipant meetupRoomParticipant);

    void delete(MeetupRoomParticipant participant);

    void deleteByChatRoomId(Long roomId);
}
