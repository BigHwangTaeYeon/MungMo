package mungmo.chat.com.config.websocket.function;

import mungmo.chat.com.exception.NotFoundException;
import mungmo.chat.domain.room.facade.MeetupRoomParticipantFacade;
import mungmo.chat.domain.room.service.MeetupRoomService;
import mungmo.chat.response.member.service.MemberService;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

public class ParticipantFunction extends ChatFunctionByStatus {
    private final MeetupRoomParticipantFacade participantFacade;

    public ParticipantFunction(MemberService memberService, MeetupRoomService meetupRoomService, MeetupRoomParticipantFacade participantFacade) {
        super(memberService, meetupRoomService);
        this.participantFacade = participantFacade;
    }

    @Override
    public void validation(StompHeaderAccessor accessor) throws NotFoundException {
        Optional.of(accessor)
                .filter(acc -> Objects.requireNonNull(acc.getNativeHeader("userId")).isEmpty())
                .filter(acc -> Objects.requireNonNull(acc.getNativeHeader("roomNum")).isEmpty())
                .filter(ac ->
                        // 데이터가 존재하면 true
                        !ObjectUtils.isEmpty(participantFacade.findByUserIdAndChatRoomId(
                                Long.parseLong(ac.getNativeHeader("userId").get(0)),
                                Long.parseLong(ac.getNativeHeader("roomNum").get(0))
                        ))
                )
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void join(StompHeaderAccessor accessor) {
        // 채팅 참여
        participantFacade.joinChatMeetup(getUserId(accessor), getRoomNum(accessor));
        // 채팅 읽음 처리
        participantFacade.changeStatusReadTrue(getUserId(accessor), getRoomNum(accessor));
    }

    @Override
    public void exit(StompHeaderAccessor accessor) {
        participantFacade.exitChatMeetup(getUserId(accessor), getRoomNum(accessor));
    }
}
