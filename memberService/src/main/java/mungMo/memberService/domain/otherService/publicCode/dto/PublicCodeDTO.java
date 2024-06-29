package mungMo.memberService.domain.otherService.publicCode.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class PublicCodeDTO {
    private String codeType;
    private int code;
    private String codeName;
}
