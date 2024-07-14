//package mungmo.mungmoChat.domain.test.chat.controller;
//
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import mungmo.mungmoChat.domain.test.chat.dto.test;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j
////@RequestMapping("/v1")
//public class TestChatController {
//
//    private final SimpMessagingTemplate simpMessagingTemplate;  // (1)
//
//    public TestChatController(SimpMessagingTemplate simpMessagingTemplate) {
//        this.simpMessagingTemplate = simpMessagingTemplate;
//    }
//
//    @MessageMapping("/chattings/{chattingRoomId}/messages")  // (2)
//    public void chat(@DestinationVariable Long chattingRoomId, test chattingRequest) {  // (3)
//        simpMessagingTemplate.convertAndSend("/subscription/chattings/" + chattingRoomId, chattingRequest.getMessage());
//        log.info("Message [{}] send by member: {} to chatting room: {}", chattingRequest.getMessage(), chattingRequest.getId(), chattingRoomId);
//    }
//
//    // stompConfig 에서 설정한 applicationDestinationPrefixes 와 @MessageMapping 경로가 병합됨 (/pub + ...)
//    // /pub/chat/enter 에 메세지가 오면 동작
//    @MessageMapping(value = "/chat/enter")
//    public void enter(test message){ // 채팅방 입장
////        message.setMessage(message.getId() + "님이 채팅방에 참여하였습니다.");
//        simpMessagingTemplate.convertAndSend("/sub/chat/" + message.getRoomId(), message);
//    }
//
//    // /pub/chat/message 에 메세지가 오면 동작
//    @MessageMapping(value = "/chat/message")
//    public void message(test message){
////        ChatResponseDto savedMessage = chatService.saveMessage(message);
//        simpMessagingTemplate.convertAndSend("/sub/chat/" + 1, message);
////        simpMessagingTemplate.convertAndSend("/sub/chat/" + savedMessage.getRoomId(), savedMessage);
//    }
//
//    @MessageMapping("/chatroom") // 실제론 메세지 매핑으로 pub/chatroom/{id} 임
//    public void sendMessage(test message) {
//        System.out.println("message = " + message);
////        chatService.saveMessage(chatDTO);
//        // /sub/chatroom/{id}로 메세지 보냄
////        template.convertAndSend("/sub/chatroom/" + chatDTO.getRoomId(), chatDTO);
//    }
//
//    /**
//     * 신고 종류
//     * @return
//     */
//    @GetMapping("reportType")
//    public ResponseEntity<?> reportType() {
//        return ResponseEntity.ok(ResponseEntity.ok());
//    }
//
//    @GetMapping("test")
//    public String test() {
//        return "test";
//    }
//
//    @Getter
//    public static class data<T> {
//        private final T data;
//        public data(T data) {
//            this.data = data;
//        }
//    }
//}
