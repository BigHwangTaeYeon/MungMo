package mungmo.chat.domain.massage.external;

import lombok.*;
import mungmo.chat.domain.massage.domain.ChatType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class Message implements Serializable {

    private Long chatRoomId;

    private Long senderId;

    private String senderNickName;

    private String content;

    private LocalDateTime createdAt;

    private int readCount;

    // 소모임, 번개
    private ChatType chatType;

    private String imageName; // 이미지 파일 이름
    private String imageUrl; // 이미지 URL
}
