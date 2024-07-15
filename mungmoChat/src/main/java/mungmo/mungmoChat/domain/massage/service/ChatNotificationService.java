package mungmo.mungmoChat.domain.massage.service;

import mungmo.mungmoChat.domain.massage.domain.ChatNotification;
import mungmo.mungmoChat.domain.massage.dto.Notification;
import mungmo.mungmoChat.domain.massage.repository.ChatNotificationRepository;
import mungmo.mungmoChat.otherDomain.member.entity.MemberEntity;
import mungmo.mungmoChat.otherDomain.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatNotificationService {
    private final ChatNotificationRepository chatNotificationRepository;
    private final MemberService memberService;
//    private final ChatRoomParticipantService chatRoomParticipantService;

    public ChatNotificationService(
            ChatNotificationRepository chatNotificationRepository,
            MemberService memberService
//            , ChatRoomParticipantService chatRoomParticipantService
    ) {
        this.chatNotificationRepository = chatNotificationRepository;
        this.memberService = memberService;
//        this.chatRoomParticipantService = chatRoomParticipantService;
    }

    public void saveNotification(Notification notification) {
        // 보내는 사람
        MemberEntity recipientId = memberService.findMemberById(notification.getSendId());
        // 받는 사람 찾기
//        List<ChatRoomParticipantDTO> allParticipant = chatRoomParticipantService.findAllParticipant(notification.getChatRoomId());

        chatNotificationRepository.save(ChatNotification.of(recipientId, notification));
    }
}
