package mungmo.chat.domain.room.infra.repository;

import mungmo.chat.domain.room.entity.MeetupRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaMeetupRoomRepository extends JpaRepository<MeetupRoom, Long> {
}
