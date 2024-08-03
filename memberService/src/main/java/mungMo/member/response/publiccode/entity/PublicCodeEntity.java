package mungMo.member.response.publiccode.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.entity.MemberTypeEntity;
import mungMo.member.response.publiccode.external.PublicCodeDto;

@Entity
@Table(name = "public_code")
@NoArgsConstructor
public class PublicCodeEntity {
    @Id @GeneratedValue
    @Column(name = "public_code_id")
    private Long id;

    @Getter
    @Column(name = "code_type")
    private String codeType;

    @Getter
    private int code;

    @Getter
    @Column(name = "code_name")
    private String codeName;

    @Column(name = "use_yn")
    private boolean useYN;

    public PublicCodeDto toDto() {
        return PublicCodeDto.builder()
                .codeType(codeType)
                .code(code)
                .codeName(codeName)
                .build();
    }

    public MemberTypeEntity toMemberType(PublicCodeEntity publicCode, MemberEntity member) {
        return new MemberTypeEntity(publicCode, member);
    }
}
