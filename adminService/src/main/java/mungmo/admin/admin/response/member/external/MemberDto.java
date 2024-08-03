package mungmo.admin.admin.response.member.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import mungmo.admin.admin.response.member.entity.MemberAuthority;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
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
    private String dogImgName;
    private String dogImgUrl;
    private FcmTokenDto fcmTokenDTO;

}
