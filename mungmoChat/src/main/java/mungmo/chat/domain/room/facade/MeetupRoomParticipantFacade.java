package mungmo.chat.domain.room.facade;

import lombok.RequiredArgsConstructor;
import mungmo.chat.domain.room.entity.MeetupRoomParticipant;
import mungmo.chat.domain.room.external.MeetupRoomParticipantDto;
import mungmo.chat.domain.room.service.MeetupRoomParticipantService;
import mungmo.chat.response.notification.service.ChatNotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetupRoomParticipantFacade {
    private final MeetupRoomParticipantService participantService;
    private final ChatNotificationService chatNotificationService;

    @Transactional(readOnly = true)
    public List<MeetupRoomParticipantDto> participantListById(Long chatRoomId) {
        return participantService.participantListByRoom(chatRoomId)
                .stream()
                .map(MeetupRoomParticipant::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MeetupRoomParticipantDto> chatNonParticipants(Long chatRoomId) {
        return participantService.chatNonParticipants(chatRoomId)
                .stream()
                .map(MeetupRoomParticipant::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeetupRoomParticipantDto findByUserIdAndChatRoomId(Long userId, Long chatRoomId) {
        return participantService.findByUserIdAndChatRoomId(userId, chatRoomId).toDto();
    }

    @Transactional
    public void joinChatMeetup(Long userId, Long chatRoomId) {
        MeetupRoomParticipant participant = participantService.findByUserIdAndChatRoomId(userId, chatRoomId);
        participantService.joinChatMeetup(participant);
    }

    @Transactional
    public void exitChatMeetup(Long userId, Long chatRoomId) {
        MeetupRoomParticipant participant = participantService.findByUserIdAndChatRoomId(userId, chatRoomId);
        participantService.exitChatMeetup(participant);
    }

    @Transactional
    public void joinMeetup(MeetupRoomParticipantDto participantDTO) {
        participantService.joinMeetup(participantDTO);
    }

    @Transactional
    public void exitMeetup(MeetupRoomParticipantDto participantDTO) {
        MeetupRoomParticipant participant = participantService.findByUserIdAndChatRoomId(participantDTO.getMemberId(), participantDTO.getChatRoomId());
        participantService.exitMeetup(participant);
    }

    @Transactional
    public void deleteAllParticipant(Long roomId) {
        participantService.deleteAllParticipant(roomId);
    }

    @Transactional
    public void changeStatusReadTrue(Long roomId, Long userId) {
        chatNotificationService.changeStatusReadTrue(roomId, userId);
    }
}
