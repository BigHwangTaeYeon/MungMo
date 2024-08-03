package mungmo.board.response.member.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {
    private Long memberId;
    private String email;
    private String dogName;
    private String dogLike;
    private String gender;
    private String ageRange;
    private int mannerTemperature;
    private boolean townCertificated;
}
