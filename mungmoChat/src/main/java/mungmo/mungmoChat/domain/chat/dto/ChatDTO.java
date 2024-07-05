package mungmo.mungmoChat.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ChatDTO {
    private String codeType;
    private int code;
    private String codeName;
}
