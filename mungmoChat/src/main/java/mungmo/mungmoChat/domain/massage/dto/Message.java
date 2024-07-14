package mungmo.mungmoChat.domain.massage.dto;

import lombok.*;
import mungmo.mungmoChat.domain.massage.domain.ChatMessage;
import mungmo.mungmoChat.domain.massage.domain.ChatType;

import java.io.Serializable;

@Getter @Setter
@ToString
@Builder
public class Message implements Serializable {

    private Long chatRoomId;

    private Long senderId;

    private String content;

    private String createdAt;
    private int readCount;

    private ChatType chatType; // 채팅 타입 필드 추가('TEXT', 'IMAGE')

    private String imageName; // 이미지 파일 이름
    private String imageUrl; // 이미지 URL

    public Message() {
    }

    public Message(Long chatRoomId, Long senderId, String content, String createdAt, int readCount, ChatType chatType, String imageName, String imageUrl) {
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.createdAt = createdAt;
        this.readCount = readCount;
        this.chatType = chatType;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    private Message(Long senderId, String content) {
        this.senderId = senderId;
        this.content = content;
    }

    public static Message of(Long senderId, String content) {
        return new Message(senderId, content);
    }

    public void prepareMessageForSending(Long senderId, String createdAt, int readCount) {
        this.senderId = senderId;
        this.createdAt = createdAt;
        this.readCount = readCount;
    }

    public ChatMessage convertToChatMessage() {
        return ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .content(content)
                .createdAt(createdAt)
                .readCount(readCount)
                .chatType(chatType)
                .imageName(imageName)
                .imageUrl(imageUrl)
                .build();
    }
}