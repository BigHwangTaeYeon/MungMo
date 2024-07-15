package mungmo.mungmoChat.com.config.websocket.function;

import mungmo.mungmoChat.domain.room.service.ChatRoomService;
import mungmo.mungmoChat.otherDomain.member.service.MemberService;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.util.Optional;

public abstract class ChatFunctionByStatus {

    protected final MemberService memberService;
    protected final ChatRoomService chatRoomService;

    public ChatFunctionByStatus(MemberService memberService, ChatRoomService chatRoomService) {
        this.memberService = memberService;
        this.chatRoomService = chatRoomService;
    }

    public abstract void validation(StompHeaderAccessor accessor);
    public abstract void create(StompHeaderAccessor accessor);
    public abstract void delete(StompHeaderAccessor accessor);

    protected Long getUserId(StompHeaderAccessor accessor) {
        return Optional.of(accessor)
                .map(acc -> Long.parseLong(acc.getNativeHeader("userId").get(0)))
                .orElseThrow(IllegalAccessError::new);
    }

    protected Long getRoomNum(StompHeaderAccessor accessor) {
        return Optional.ofNullable(accessor)
                .map(acc -> Long.parseLong(acc.getNativeHeader("roomNum").get(0)))
                .orElseThrow(IllegalAccessError::new);
    }
}
