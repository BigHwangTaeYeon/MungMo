package mungMo.memberService.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mungMo.memberService.domain.member.entity.MemberAuthority;

@Getter
@Setter
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

    public MemberDTO() {
    }

    @Builder
    public MemberDTO(Long memberId, String email, String dogName, String dogLike, String gender, String ageRange, int mannerTemperature, boolean townCertificated, MemberAuthority authority) {
        this.memberId = memberId;
        this.email = email;
        this.dogName = dogName;
        this.dogLike = dogLike;
        this.gender = gender;
        this.ageRange = ageRange;
        this.mannerTemperature = mannerTemperature;
        this.townCertificated = townCertificated;
        this.authority = authority;
    }
}
