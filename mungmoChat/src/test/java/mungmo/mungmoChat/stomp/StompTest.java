package mungmo.mungmoChat.stomp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import mungmo.mungmoChat.domain.massage.dto.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockito.ArgumentMatchers.any;

//@UnitTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StompTest {
//    @MockBean
//    private HandleMessageUseCase handleMessageUseCase;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @LocalServerPort
    private int port;

    private final String SEND_MESSAGE_ENDPOINT = "/send/chat";
    private final String SUBSCRIBE_ENDPOINT = "/sub/chat/3";
    private String URL;

    WebSocketStompClient stompClient;
    CompletableFuture<Message> arriveMessage;
    StompSessionHandlerAdapter handler;

    @BeforeEach
    void setUp() {
        URL = "ws://localhost:%d/stomp/chat".formatted(port);
        arriveMessage = new CompletableFuture<>();
        // websocket, sockjs, stomp 준비, 직렬화 설정
        stompClient = new WebSocketStompClient(
                new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));

        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        ObjectMapper objectMapper = messageConverter.getObjectMapper();
        objectMapper.registerModules(new JavaTimeModule(), new ParameterNamesModule());
        stompClient.setMessageConverter(messageConverter);

        // stomp message handler
        handler = new StompSessionHandlerAdapter() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                arriveMessage.complete((Message)payload);
            }
        };
    }
    @DisplayName("message send")
    @Test
    void success_message_send() throws ExecutionException, InterruptedException, TimeoutException {
        // given
        StompHeaders headers = new StompHeaders();
        headers.add("userId", "1");
        StompSession stompSession = stompClient.connectAsync(URL, new WebSocketHttpHeaders(),
                        headers,
                        new StompSessionHandlerAdapter() {
                        })
                .get(1, SECONDS);
        stompSession.subscribe(SUBSCRIBE_ENDPOINT, handler);

        //when
//        stompSession.send(SEND_MESSAGE_ENDPOINT+"/roomId", Message.of("secondRoom", "123", "hello"));


        //then
//        assertThat(stompSession.isConnected()).isTrue();
//        verify(jwtUtils, times(1)).isValidToken(any());
//        verify(jwtUtils, times(1)).parseMemberIdFromToken(any());
//        verify(handleMessageUseCase, times(1)).publishMessage(any());
    }
}
