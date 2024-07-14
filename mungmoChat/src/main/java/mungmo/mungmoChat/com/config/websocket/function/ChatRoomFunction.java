package mungmo.mungmoChat.com.config.websocket.function;

import mungmo.mungmoChat.domain.room.service.ChatRoomService;
import mungmo.mungmoChat.otherDomain.member.service.MemberService;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public class ChatRoomFunction extends ChatFunctionByStatus {

    public ChatRoomFunction(MemberService memberService, ChatRoomService chatRoomService) {
        super(memberService, chatRoomService);
    }

    @Override
    public void create(StompHeaderAccessor accessor) {
        chatRoomService.createChatRoom(memberService.findMemberById(getUserId(accessor)));
    }

    @Override
    public void delete(StompHeaderAccessor accessor) {
    }
}
