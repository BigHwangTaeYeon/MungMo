package mungMo.member.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mungMo.member.domain.member.external.MemberTypeDto;
import mungMo.member.response.publiccode.entity.PublicCodeEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_type")
@NoArgsConstructor
public class MemberTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_type_id")
    private Long id;

    private boolean use;
    private LocalDateTime update_date;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Getter
    @OneToOne
    private PublicCodeEntity publicCode;

    public void useY() {
        this.use = true;
    }

    public void useN() {
        this.use = false;
    }

    public MemberTypeEntity(PublicCodeEntity publicCode, MemberEntity member) {
        this.publicCode = publicCode;
        this.use = false;
        this.member = member;
        this.update_date = LocalDateTime.now();
    }

    public MemberTypeDto toDto() {
        return MemberTypeDto.builder()
                .type(publicCode.getCodeType())
                .code(publicCode.getCode())
                .codeName(publicCode.getCodeName())
                .use(use)
                .build();
    }
}
