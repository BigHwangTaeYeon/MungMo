package mungmo.mungmoChat.com.config.websocket.function;

import mungmo.mungmoChat.domain.room.service.ChatRoomParticipantService;
import mungmo.mungmoChat.domain.room.service.ChatRoomService;
import mungmo.mungmoChat.otherDomain.member.service.MemberService;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public class ParticipantFunction extends ChatFunctionByStatus {
    private final ChatRoomParticipantService chatRoomParticipantService;

    public ParticipantFunction(MemberService memberService, ChatRoomService chatRoomService, ChatRoomParticipantService chatRoomParticipantService) {
        super(memberService, chatRoomService);
        this.chatRoomParticipantService = chatRoomParticipantService;
    }

    @Override
    public void validation(StompHeaderAccessor accessor) {
//        Optional.of(accessor)
//            .filter(acc -> Objects.requireNonNull(acc.getNativeHeader("userId")).isEmpty())
//            .filter(acc -> Objects.requireNonNull(acc.getNativeHeader("roomNum")).isEmpty());
    }

    @Override
    public void create(StompHeaderAccessor accessor) {
//        chatRoomParticipantService.joinMeetup(getUserId(accessor), getRoomNum(accessor));
    }

    @Override
    public void delete(StompHeaderAccessor accessor) {
//        chatRoomParticipantService.exitMeetup(getUserId(accessor), getRoomNum(accessor));
    }
}
