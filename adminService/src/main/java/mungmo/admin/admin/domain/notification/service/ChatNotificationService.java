package mungmo.admin.admin.domain.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mungmo.admin.admin.domain.notification.external.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import mungmo.admin.admin.domain.notification.domain.ChatNotification;
import mungmo.admin.admin.domain.notification.repository.ChatNotificationRepository;
import mungmo.admin.admin.response.member.entity.MemberEntity;
import mungmo.admin.admin.response.room.external.MeetupRoomParticipantDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatNotificationService {
    private final ChatNotificationRepository chatNotificationRepository;

    @Transactional
    public void saveNotification(MemberEntity recipientId, MeetupRoomParticipantDto participant, Notification notification) {
        chatNotificationRepository.save(ChatNotification.of(recipientId, participant, notification));
    }

    public void sendPushNotification(Notification notification, List<MemberEntity> notInChatMemberList) {
        for(MemberEntity entity : notInChatMemberList) {
            try {
                if(StringUtils.hasText(entity.getFcmToken().getFcmToken())) {
                    Message message = Message.builder()
                            .putData("sender", notification.getSenderNick())
                            .putData("body", notification.getContent())
                            .setToken(entity.getFcmToken().getFcmToken())
                            .build();
                    FirebaseMessaging.getInstance().send(message);
                }
            } catch (Exception e) {
                log.debug("FirebaseMessaging e.getMessage({}) = ", e.getMessage());
            }
        }
    }

    @Transactional
    public void changeStatusReadTrue(Long userId, Long roomNum) {
        chatNotificationRepository.findByRecipientIdAndChatRoomId(userId, roomNum)
                .forEach(ChatNotification::changeStatusReadTrue);
    }
}
