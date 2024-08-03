package mungMo.member.domain.member.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class MemberIdAndDogNameDto {
    private Long id;
    private String dogName;
}
