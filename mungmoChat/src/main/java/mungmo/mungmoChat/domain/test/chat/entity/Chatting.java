//package mungmo.mungmoChat.domain.chat.entity;
//
//import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//
//@Document(collection = "chatting")
//public class Chatting {
//
//    @Id
//    private String id;
//    private Integer chatRoomNo;
//    private Integer senderNo;
//    private String senderName;
//    private String contentType;
//    private String content;
//    private LocalDateTime sendDate;
//    private long readCount;
//
//    public Chatting() {
//    }
//
//    @Builder
//    public Chatting(String id, Integer chatRoomNo, Integer senderNo, String senderName, String contentType, String content, LocalDateTime sendDate, long readCount) {
//        this.id = id;
//        this.chatRoomNo = chatRoomNo;
//        this.senderNo = senderNo;
//        this.senderName = senderName;
//        this.contentType = contentType;
//        this.content = content;
//        this.sendDate = sendDate;
//        this.readCount = readCount;
//    }
//
//    @Override
//    public String toString() {
//        return "Chatting{" +
//                "id='" + id + '\'' +
//                ", chatRoomNo=" + chatRoomNo +
//                ", senderNo=" + senderNo +
//                ", senderName='" + senderName + '\'' +
//                ", contentType='" + contentType + '\'' +
//                ", content='" + content + '\'' +
//                ", sendDate=" + sendDate +
//                ", readCount=" + readCount +
//                '}';
//    }
//}