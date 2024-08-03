package mungMo.member.domain.member.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberTypeDto {
    private String type;
    private int code;
    private String codeName;
    private boolean use;
}
