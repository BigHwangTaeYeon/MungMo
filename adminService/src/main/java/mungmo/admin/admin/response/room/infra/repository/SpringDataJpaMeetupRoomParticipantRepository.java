package mungmo.admin.admin.response.room.infra.repository;

import mungmo.admin.admin.response.room.entity.MeetupRoomParticipant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaMeetupRoomParticipantRepository extends MongoRepository<MeetupRoomParticipant, Long> {

}
