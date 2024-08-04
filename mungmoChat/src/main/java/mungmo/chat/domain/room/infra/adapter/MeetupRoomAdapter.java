package mungmo.chat.domain.room.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.room.entity.MeetupRoom;
import mungmo.chat.domain.room.infra.MeetupRoomRepository;
import mungmo.chat.domain.room.infra.repository.SpringDataJpaMeetupRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MeetupRoomAdapter implements MeetupRoomRepository {
    private final SpringDataJpaMeetupRoomRepository meetupRoomRepository;

    @Override
    public Optional<MeetupRoom> findById(Long roomId) {
        return meetupRoomRepository.findById(roomId);
    }

    @Override
    public List<MeetupRoom> findAll() {
        return meetupRoomRepository.findAll();
    }

    @Override
    public void save(MeetupRoom room) {
        meetupRoomRepository.save(room);
    }

    @Override
    public void deleteById(Long roomId) {
        meetupRoomRepository.deleteById(roomId);
    }
}
