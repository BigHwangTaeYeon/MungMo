package mungmo.mungmoChat.domain.test.chat.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class test implements Serializable {

    private String roomId;

    private String id;

    private String message;

}
