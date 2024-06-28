package mungMo.memberService.domain.member.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mungMo.memberService.domain.embede.FileInfo;
import mungMo.memberService.domain.town.entity.TownEntity;

@Getter
@Setter
@Builder
public class MemberDTO {
    private Long memberId;
    private String email;
    private String nickName;
    private String gender;
    private int mannerTemperature;
    private boolean townCertificated;
    private String original_name;
    private String mask_name;
    private String file_path;
    private String file_type;
}
