package mungmo.mungmoChat.com.config.websocket;

import lombok.extern.slf4j.Slf4j;
import mungmo.mungmoChat.com.config.websocket.function.ChatFunctionByStatus;
import mungmo.mungmoChat.com.config.websocket.function.ParticipantFunction;
import mungmo.mungmoChat.domain.room.service.ChatRoomParticipantService;
import mungmo.mungmoChat.domain.room.service.ChatRoomService;
import mungmo.mungmoChat.otherDomain.member.service.MemberService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class StompHandler implements ChannelInterceptor { // WebSocket을 이용한 채팅 기능에서 메시지를 가공하고 처리하는 역할

    private final MemberService memberService;
    private final ChatRoomService chatRoomService;
    private final ChatRoomParticipantService chatRoomParticipantService;

    private ChatFunctionByStatus function;

    public StompHandler(MemberService memberService, ChatRoomService chatRoomService, ChatRoomParticipantService chatRoomParticipantService) {
        this.memberService = memberService;
        this.chatRoomService = chatRoomService;
        this.chatRoomParticipantService = chatRoomParticipantService;
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
                function = new ParticipantFunction(memberService, chatRoomService, chatRoomParticipantService);
                handleConnect(accessor);
                break;
            case SUBSCRIBE:
                System.out.println("SUBSCRIBE");
                handleSubscribe(accessor);
                break;
            case SEND:
                System.out.println("SEND");
                handleSend(accessor);
                break;
            case DISCONNECT:
                System.out.println("DISCONNECT");
                function = new ParticipantFunction(memberService, chatRoomService, chatRoomParticipantService);
                handleDisconnect(accessor);
                break;
            case ERROR:
                System.out.println("WebSocket Error 처리 코드!!");
                handleUnsubscribe(accessor);
                break;
        }
    }


    private void handleConnect(StompHeaderAccessor accessor) {
        function.validation(accessor);
        function.create(accessor);
    }

    private void handleSubscribe(StompHeaderAccessor accessor) {
        System.out.println("handleSubscribe");
    }

    private void handleSend(StompHeaderAccessor accessor) {
        System.out.println("handleSubscribe");
    }

    private void handleUnsubscribe(StompHeaderAccessor accessor) {
        System.out.println("handleUnsubscribe");
        String destination = accessor.getDestination();
        handleChatRoomUnsubscription(accessor);
    }

    private void handleDisconnect(StompHeaderAccessor accessor) {
        System.out.println("웹소켓 DISCONNECT");
        handleChatRoomUnsubscription(accessor);
    }

    private void handleChatRoomUnsubscription(StompHeaderAccessor accessor) {
        System.out.println("채팅방 UNSUBSCRIBE");
        function.validation(accessor);
        function.delete(accessor);
    }
}