package mungMo.memberService.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import mungMo.memberService.com.util.GetDate;
import mungMo.memberService.domain.member.dto.MemberTypeDTO;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_type")
public class MemberTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_type_id")
    private Long id;

    private String type;
    @Getter
    private int code;
    private boolean use;
    private LocalDateTime update_date;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    public void useY() {
        this.use = true;
    }

    public void useN() {
        this.use = false;
    }

    public MemberTypeEntity() {
    }

    public MemberTypeEntity(String codeType, int code, MemberEntity member) {
        this.type = codeType;
        this.code = code;
        this.use = false;
        this.member = member;
        this.update_date = GetDate.pareLocalDataTime("yyyyMMddHHmmss");
    }

    public MemberTypeDTO changeToDTO() {
        return MemberTypeDTO.builder()
                .type(type)
                .code(code)
                .use(use)
                .build();
    }
}
