package mungMo.member.response.publiccode.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class PublicCodeDto {
    private String codeType;
    private int code;
    private String codeName;
}
