package mungMo.member.domain.member.external;

import lombok.*;
import mungMo.member.domain.member.entity.MemberAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private MemberAuthority authority;
}
