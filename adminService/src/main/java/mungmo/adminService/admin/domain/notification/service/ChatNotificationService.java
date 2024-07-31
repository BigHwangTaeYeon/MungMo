package mungmo.adminService.admin.domain.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mungmo.adminService.admin.com.filter.ObjectConverter;
import mungmo.adminService.admin.domain.notification.dto.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import mungmo.adminService.admin.domain.notification.domain.ChatNotification;
import mungmo.adminService.admin.domain.notification.repository.ChatNotificationRepository;
import mungmo.adminService.admin.otherDomain.member.entity.MemberEntity;
import mungmo.adminService.admin.otherDomain.member.dto.MemberDTO;
import mungmo.adminService.admin.otherDomain.member.service.MemberService;
import mungmo.adminService.admin.otherDomain.room.dto.MeetupRoomParticipantDTO;
import mungmo.adminService.admin.otherDomain.room.service.MeetupRoomParticipantServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatNotificationService {
    private final ChatNotificationRepository chatNotificationRepository;
    private final MemberService memberService;
    private final MeetupRoomParticipantServiceClient participantServiceClient;

    public ChatNotificationService(ChatNotificationRepository chatNotificationRepository, MemberService memberService, MeetupRoomParticipantServiceClient participantServiceClient) {
        this.chatNotificationRepository = chatNotificationRepository;
        this.memberService = memberService;
        this.participantServiceClient = participantServiceClient;
    }

    @Transactional(readOnly = true)
    private List<MeetupRoomParticipantDTO> chatNonParticipantsByFeignClient(Long roomId) {
        ResponseEntity<?> res = participantServiceClient.chatNonParticipants(roomId);
//        ObjectMapper mapper = new ObjectMapper();
//
//        List<?> list = (List<?>) ObjectConverter.jsonBody(res);
//
//        return list.stream()
//                .map(body -> mapper.convertValue(body, MeetupRoomParticipantDTO.class))
//                .collect(Collectors.toCollection(ArrayList::new));
        return ObjectConverter.operatingList(res, MeetupRoomParticipantDTO.class);
    }

    @Transactional
    public void saveNotification(Notification notification) {
        // 보내는 사람
        MemberEntity recipientId = memberService.findMemberById(notification.getSendId());
        // 채팅 참여중이지 않은 사람
        // feignClient 필요
//        List<MeetupRoomParticipantDTO> participants = participantServiceClient.chatNonParticipants(notification.getChatRoomId());
        List<MeetupRoomParticipantDTO> participants = chatNonParticipantsByFeignClient(notification.getChatRoomId());

        for (MeetupRoomParticipantDTO participant : participants) {
            chatNotificationRepository.save(ChatNotification.of(recipientId, participant, notification));
        }
    }

    @Transactional(readOnly = true)
    public void sendPushNotification(Notification notification) {
        // 채팅 참여중이지 않은 사람
        // feignClient 필요
//        List<MeetupRoomParticipantDTO> participants = participantServiceClient.chatNonParticipants(notification.getChatRoomId());
        List<MeetupRoomParticipantDTO> participants = chatNonParticipantsByFeignClient(notification.getChatRoomId());

        try {
            for (MeetupRoomParticipantDTO participant : participants) {
                MemberDTO notInChatMember = memberService.getMemberByFeignClient(participant.getMemberId());
                if(StringUtils.hasText(notInChatMember.getFcmTokenDTO().getFcmToken())) {
                    Message message = Message.builder()
                            .putData("sender", notification.getSenderNick())
                            .putData("body", notification.getContent())
                            .setToken(notInChatMember.getFcmTokenDTO().getFcmToken())
                            .build();
                    FirebaseMessaging.getInstance().send(message);
                }
            }
        } catch (Exception e) {
            System.out.println("FirebaseMessaging e.getMessage() = " + e.getMessage());
        }
    }

    @Transactional
    public void changeStatusReadTrue(Long userId, Long roomNum) {
        chatNotificationRepository.findByRecipientIdAndChatRoomId(userId, roomNum)
                .forEach(ChatNotification::changeStatusReadTrue);
    }
}
