package mungmo.chat.com.config.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mungmo.chat.com.config.websocket.function.ChatFunctionByStatus;
import mungmo.chat.com.config.websocket.function.ParticipantFunction;
import mungmo.chat.com.exception.NotFoundException;
import mungmo.chat.domain.room.facade.MeetupRoomParticipantFacade;
import mungmo.chat.domain.room.service.MeetupRoomParticipantService;
import mungmo.chat.domain.room.service.MeetupRoomService;
import mungmo.chat.response.notification.external.ChatNotificationClient;
import mungmo.chat.response.member.service.MemberService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final MemberService memberService;
    private final MeetupRoomService meetupRoomService;
    private final MeetupRoomParticipantFacade participantFacade;

    private ChatFunctionByStatus function;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) { // 메시지의 유효성 검사나 변형 처리
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand stompCommand = accessor.getCommand();
        System.out.println("preSend");
        // CONNECT, SUBSCRIBE, SEND, DISCONNECT
        if (stompCommand != null) {
            try {
                handleStompCommand(stompCommand, accessor);
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return ChannelInterceptor.super.preSend(message, channel);
    }

    private void handleStompCommand(StompCommand stompCommand, StompHeaderAccessor accessor) throws NotFoundException {
        switch (stompCommand) {
            case CONNECT:
                System.out.println("CONNECT");
                function = new ParticipantFunction(memberService, meetupRoomService, participantFacade);
//                handleConnect(accessor);
                break;
            case SUBSCRIBE:
                System.out.println("SUBSCRIBE");
//                handleSubscribe(accessor);
                break;
            case SEND:
                System.out.println("SEND");
//                handleSend(accessor);
                break;
            case DISCONNECT:
                System.out.println("DISCONNECT");
                function = new ParticipantFunction(memberService, meetupRoomService, participantFacade);
//                handleDisconnect(accessor);
                break;
            case ERROR:
                System.out.println("WebSocket Error 처리 코드!!");
//                handleUnsubscribe(accessor);
                break;
        }
    }

    private void handleConnect(StompHeaderAccessor accessor) throws NotFoundException {
        // 모임에 참여자 존재 여부 확인
        function.validation(accessor);
        // 모임 참여자 채팅 참여
        function.join(accessor);
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
//        function.validation(accessor);
        function.exit(accessor);
    }
}