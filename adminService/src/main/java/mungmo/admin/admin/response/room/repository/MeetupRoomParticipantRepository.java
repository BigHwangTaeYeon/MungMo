package mungmo.admin.admin.response.room.repository;

import mungmo.admin.admin.response.room.entity.MeetupRoomParticipant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetupRoomParticipantRepository extends MongoRepository<MeetupRoomParticipant, Long> {
}
