package mungmo.admin.admin.domain.publiccode.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mungmo.admin.admin.domain.publiccode.external.PublicCodeDto;

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
}
