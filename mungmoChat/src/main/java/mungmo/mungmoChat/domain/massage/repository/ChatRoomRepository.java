package mungmo.mungmoChat.domain.massage.repository;

import mungmo.mungmoChat.domain.room.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
