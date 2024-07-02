package mungMo.boardService.domain.otherService.publicCode.entity;

import jakarta.persistence.*;
import lombok.Getter;
import mungMo.boardService.domain.otherService.publicCode.dto.PublicCodeDTO;

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

    public PublicCodeDTO changeToDTO() {
        return PublicCodeDTO.builder()
                .codeType(codeType)
                .code(code)
                .codeName(codeName)
                .build();
    }
}
