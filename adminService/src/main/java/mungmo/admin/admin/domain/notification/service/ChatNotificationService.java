package mungmo.admin.admin.domain.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mungmo.admin.admin.domain.notification.infra.adapter.NotificationAdapter;
import mungmo.admin.admin.domain.notification.vo.ChatNotificationVo;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import mungmo.admin.admin.domain.notification.domain.ChatNotification;
import mungmo.admin.admin.domain.notification.infra.NotificationRepository;
import mungmo.admin.admin.domain.notification.infra.repository.SpringDataJpaChatNotificationRepository;
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
    private final NotificationRepository notificationRepository;
    private final SpringDataJpaChatNotificationRepository chatNotificationRepository;

    @Transactional
    public void saveNotification(MemberEntity sender, MeetupRoomParticipantDto participant, ChatNotificationVo notification) {
//        notificationRepository.save(ChatNotification.of(sender, participant, notification));
        notificationRepository.save(ChatNotificationVo.of(sender, participant, notification));
    }

    public void sendPushNotification(ChatNotificationVo notification, List<MemberEntity> notInChatMemberList) {
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
        notificationRepository.findByRecipientIdAndChatRoomId(userId, roomNum)
                .forEach(ChatNotificationVo::changeStatusReadTrue);
    }
}
