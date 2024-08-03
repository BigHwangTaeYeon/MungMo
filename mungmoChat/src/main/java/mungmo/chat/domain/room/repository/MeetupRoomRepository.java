package mungmo.chat.domain.room.repository;

import mungmo.chat.domain.room.entity.MeetupRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetupRoomRepository extends JpaRepository<MeetupRoom, Long> {
}
