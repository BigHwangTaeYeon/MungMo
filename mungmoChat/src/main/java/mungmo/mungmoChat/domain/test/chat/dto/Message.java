//package mungmo.mungmoChat.domain.chat.dto;
//
//import lombok.*;
//import mungmo.mungmoChat.domain.chat.entity.Chatting;
//
//import java.io.Serializable;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//
//@Getter
//@ToString
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Message implements Serializable {
//
//    @Setter
//    private String id;
//
//    private Integer chatNo;
//
//    private String contentType;
//
//    private String content;
//
//    private String senderName;
//
//    private Integer senderNo;
//
//    private Integer saleNo;
//
//    private long sendTime;
//    private Integer readCount;
//    private String senderEmail;
//
//    public void setSendTimeAndSender(LocalDateTime sendTime, Integer senderNo, String senderName, Integer readCount) {
//        this.senderName = senderName;
//        this.sendTime = sendTime.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
//        this.senderNo = senderNo;
//        this.readCount = readCount;
//    }
//
//    public Chatting convertEntity() {
//        return Chatting.builder()
//                .senderName(senderName)
//                .senderNo(senderNo)
//                .chatRoomNo(chatNo)
//                .contentType(contentType)
//                .content(content)
//                .sendDate(Instant.ofEpochMilli(sendTime).atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime())
//                .readCount(readCount)
//                .build();
//    }
//}