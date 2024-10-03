package mungmo.admin.admin.domain.notification.facade;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.domain.notification.vo.ChatNotificationVo;
import mungmo.admin.admin.domain.notification.service.ChatNotificationService;
import mungmo.admin.admin.response.member.entity.MemberEntity;
import mungmo.admin.admin.response.member.service.MemberService;
import mungmo.admin.admin.response.room.entity.MeetupRoomParticipant;
import mungmo.admin.admin.response.room.external.MeetupRoomParticipantDto;
import mungmo.admin.admin.response.room.service.MeetupRoomParticipantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationFacade {
    private final MemberService memberService;
    private final ChatNotificationService chatNotificationService;
    private final MeetupRoomParticipantService meetupRoomParticipantService;

    @Transactional
    public void saveNotification(ChatNotificationVo notification) {
        // 보내는 사람
        MemberEntity recipientId = memberService.findMemberById(notification.getSendId());
        // 채팅 참여중이지 않은 사람
        List<MeetupRoomParticipantDto> participants = meetupRoomParticipantService.chatNonParticipantsByFeignClient(notification.getChatRoomId())
                .stream()
                .map(MeetupRoomParticipant::toDto)
                .toList();

        for (MeetupRoomParticipantDto participant : participants) {
            chatNotificationService.saveNotification(recipientId, participant, notification);
        }
    }

    @Transactional(readOnly = true)
    public void sendPushNotification(ChatNotificationVo notification) {
        // 채팅 참여중이지 않은 사람
        List<MeetupRoomParticipantDto> participants = meetupRoomParticipantService.chatNonParticipantsByFeignClient(notification.getChatRoomId())
                .stream()
                .map(MeetupRoomParticipant::toDto)
                .toList();
        List<MemberEntity> notInChatMemberList = new ArrayList<>();
        for (MeetupRoomParticipantDto participant : participants) {
            notInChatMemberList.add(memberService.getMemberByFeignClient(participant.getMemberId()));
        }
        chatNotificationService.sendPushNotification(notification, notInChatMemberList);
    }

    @Transactional
    public void changeStatusReadTrue(Long userId, Long roomNum) {
        chatNotificationService.changeStatusReadTrue(userId, roomNum);
    }
}
