package mungMo.memberService.domain.otherService.publicCode.entity;

import jakarta.persistence.*;
import mungMo.memberService.domain.member.entity.MemberEntity;
import mungMo.memberService.domain.member.entity.MemberTypeEntity;

@Entity
@Table(name = "public_code")
public class PublicCodeEntity {
    @Id @GeneratedValue
    @Column(name = "public_code_id")
    private Long id;

    @Column(name = "code_type")
    private String codeType;

    private int code;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "use_yn")
    private boolean useYN;

    public MemberTypeEntity changeToMemberType(MemberEntity member) {
        return new MemberTypeEntity(codeType, code, member);
    }
}
