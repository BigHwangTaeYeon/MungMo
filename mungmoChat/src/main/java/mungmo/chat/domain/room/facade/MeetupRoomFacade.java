package mungmo.chat.domain.room.facade;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.room.entity.MeetupRoom;
import mungmo.chat.domain.room.entity.MeetupRoomParticipant;
import mungmo.chat.domain.room.external.MeetupRoomDto;
import mungmo.chat.domain.room.service.MeetupRoomParticipantService;
import mungmo.chat.domain.room.service.MeetupRoomService;
import mungmo.chat.response.member.entity.MemberEntity;
import mungmo.chat.response.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetupRoomFacade {
    private final MeetupRoomService meetupRoomService;
    private final MemberService memberService;
    private final MeetupRoomParticipantService participantService;

    public MeetupRoomDto meetupInfo(Long roomId) {
        return meetupRoomService.meetupInfo(roomId).toDto();
    }

    public List<MeetupRoomDto> meetupList() {
        return meetupRoomService.meetupList()
                .stream()
                .map(MeetupRoom::toDto)
                .collect(Collectors.toList());
    }

    public List<MeetupRoomDto> meetupListByUser(Long userId) {
        List<MeetupRoomParticipant> participants = participantService.participantListById(userId);
        return meetupRoomService.meetupListByUser(participants)
                .stream()
                .map(MeetupRoom::toDto)
                .collect(Collectors.toList());
    }

    public void createMeetup(MeetupRoomDto chatRoom, Long id) {
        MemberEntity member = memberService.findMemberById(id);
        meetupRoomService.createMeetup(chatRoom, member);
    }

    public void deleteMeetup(MeetupRoomDto chatRoom) {
        // 참여자들 모두 제거
        participantService.deleteAllParticipant(chatRoom.getRoomId());
        // 번개 방 삭제
        meetupRoomService.deleteMeetup(chatRoom);
    }
}
