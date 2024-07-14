package mungmo.mungmoChat.com.config.websocket;

import lombok.extern.slf4j.Slf4j;
import mungmo.mungmoChat.com.config.websocket.function.ChatFunctionByStatus;
import mungmo.mungmoChat.com.config.websocket.function.ChatRoomFunction;
import mungmo.mungmoChat.domain.room.service.ChatRoomService;
import mungmo.mungmoChat.otherDomain.member.service.MemberService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StompHandler implements ChannelInterceptor { // WebSocket을 이용한 채팅 기능에서 메시지를 가공하고 처리하는 역할

    private final MemberService memberService;
    private final ChatRoomService chatRoomService;
    private ChatFunctionByStatus function;

    public StompHandler(MemberService memberService, ChatRoomService chatRoomService) {
        this.memberService = memberService;
        this.chatRoomService = chatRoomService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) { // 메시지의 유효성 검사나 변형 처리
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand stompCommand = accessor.getCommand();
        System.out.println("preSend");
        // CONNECT, SUBSCRIBE, SEND, DISCONNECT
        if (stompCommand != null) {
            handleStompCommand(stompCommand, accessor);
        }
        return ChannelInterceptor.super.preSend(message, channel);
    }

    private void handleStompCommand(StompCommand stompCommand, StompHeaderAccessor accessor) {
        switch (stompCommand) {
            case CONNECT:
                System.out.println("CONNECT");
                function = new ChatRoomFunction(memberService, chatRoomService);
                handleConnect(accessor);
                break;
            case SUBSCRIBE:
                System.out.println("SUBSCRIBE");
                handleSubscribe(accessor);
                break;
            case SEND:
                System.out.println("SEND");
                break;
            case DISCONNECT:
                System.out.println("DISCONNECT");
                handleDisconnect(accessor);
                break;
            case ERROR:
                System.out.println("WebSocket Error 처리 코드!!");
                break;
        }
    }


    private void handleConnect(StompHeaderAccessor accessor) {
        function.create(accessor);
    }

    private void handleSubscribe(StompHeaderAccessor accessor) {
        System.out.println("handleSubscribe");
//        if (accessor.getDestination().startsWith(TOPIC_NOTIFICATION)) {
//            System.out.println("알림 SUBSCRIBE");
//            return;
//        }

        handleChatRoomSubscription(accessor);
    }

    private void handleChatRoomSubscription(StompHeaderAccessor accessor) {
        System.out.println("채팅방 SUBSCRIBE");


//        Long chatRoomId = getChatRoomId(accessor);
//        Long memberId = getMemberId(accessor);
//
//        // 구독하려는 채팅방 참여자가 맞는지 검증
//        validateChatRoomParticipant(chatRoomId, memberId);
//
//        updateSubscription(accessor, chatRoomId, memberId);
//
//        // 채팅방에 입장했는데 상대방이 채팅방에 입장한 상태라면, 메시지 읽음 처리 갱신 요청
//        chatService.getOtherMemberIdByChatRoomId(chatRoomId, memberId)
//                .ifPresent(otherMemberId -> notifyReadCountUpdate(chatRoomId, otherMemberId));
//
//        // 입장한 채팅방과 관련된 채팅 알림 메시지 삭제
//        notificationService.deleteAllNotificationsInChatRoomByMember(memberId, chatRoomId);
    }

    private void handleUnsubscribe(StompHeaderAccessor accessor) {
        System.out.println("handleUnsubscribe");
        String destination = accessor.getDestination();
//        if (destination != null && accessor.getDestination().startsWith(TOPIC_NOTIFICATION)) {
//            System.out.println("알림 UNSUBSCRIBE");
//            return;
//        }

        // 채팅방 구독 해지 처리
        handleChatRoomUnsubscription(accessor);
    }

    private void handleDisconnect(StompHeaderAccessor accessor) {
        System.out.println("웹소켓 DISCONNECT");
        handleChatRoomUnsubscription(accessor);
    }

    private void handleChatRoomUnsubscription(StompHeaderAccessor accessor) {
        System.out.println("채팅방 UNSUBSCRIBE");

//        Long memberId = getMemberId(accessor);
//        deleteExistingSubscription(accessor);
//        chatService.deleteChatRoomParticipantFromRedis(memberId);
    }
}