package mungmo.chat.domain.room.service;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.room.entity.MeetupRoom;
import mungmo.chat.domain.room.entity.MeetupRoomParticipant;
import mungmo.chat.domain.room.infra.MeetupRoomRepository;
import mungmo.chat.domain.room.infra.repository.SpringDataJpaMeetupRoomRepository;
import mungmo.chat.domain.room.external.MeetupRoomDto;
import mungmo.chat.response.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetupRoomService {
    private final MeetupRoomRepository roomRepository;

    public MeetupRoom meetupInfo(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow();
    }

    public List<MeetupRoom> meetupList() {
        return roomRepository.findAll();
    }

    public List<MeetupRoom> meetupListByUser(List<MeetupRoomParticipant> participants) {
        return participants
                .stream()
                .map(entity -> meetupInfo(entity.getId()))
                .collect(Collectors.toList());
    }

    public void createMeetup(MeetupRoomDto chatRoom, MemberEntity member) {
        MeetupRoom room = MeetupRoom.of(chatRoom, member);
        roomRepository.save(room);
    }

    public void deleteMeetup(MeetupRoomDto chatRoom) {
        // 번개 방 삭제
        roomRepository.deleteById(chatRoom.getRoomId());
    }
}
