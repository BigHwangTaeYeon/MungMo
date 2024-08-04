package mungmo.chat.domain.room.infra;

import mungmo.chat.domain.room.entity.MeetupRoom;
import mungmo.chat.response.member.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MeetupRoomRepository {
    Optional<MeetupRoom> findById(Long roomId);

    List<MeetupRoom> findAll();

    void save(MeetupRoom room);

    void deleteById(Long roomId);
}
