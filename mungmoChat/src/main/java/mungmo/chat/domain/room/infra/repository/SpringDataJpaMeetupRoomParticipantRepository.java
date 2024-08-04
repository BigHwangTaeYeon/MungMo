package mungmo.chat.domain.room.infra.repository;

import mungmo.chat.domain.room.entity.MeetupRoomParticipant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaMeetupRoomParticipantRepository extends MongoRepository<MeetupRoomParticipant, Long> {
    MeetupRoomParticipant findByMemberIdAndChatRoomId(Long memberId, Long chatRoomId);

    List<MeetupRoomParticipant> findByMemberId(Long userId);

    List<MeetupRoomParticipant> findByChatRoomId(Long chatRoomId);

    void deleteByChatRoomId(Long roomId);

    List<MeetupRoomParticipant> findByChatRoomIdAndJoinChat(Long chatRoomId, boolean joinChat);
}
