package mungmo.adminService.admin.otherDomain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mungmo.adminService.admin.otherDomain.member.entity.MemberAuthority;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberDTO {
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
    private FcmTokenDTO fcmTokenDTO;

    public MemberDTO() {
    }

    public MemberDTO(Long memberId, String email, String dogName, String dogLike, String gender, String ageRange, int mannerTemperature, boolean townCertificated, MemberAuthority authority, String dogImgName, String dogImgUrl, FcmTokenDTO fcmTokenDTO) {
        this.memberId = memberId;
        this.email = email;
        this.dogName = dogName;
        this.dogLike = dogLike;
        this.gender = gender;
        this.ageRange = ageRange;
        this.mannerTemperature = mannerTemperature;
        this.townCertificated = townCertificated;
        this.authority = authority;
        this.dogImgName = dogImgName;
        this.dogImgUrl = dogImgUrl;
        this.fcmTokenDTO = fcmTokenDTO;
    }
}
