package mungmo.mungmoChat.domain.chat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat")
public class ChatEntity {
    @Id
    @GeneratedValue
    @Column(name = "public_code_id")
    private Long id;

    @Column(name = "code_type")
    private String codeType;

    private int code;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "use_yn")
    private boolean useYN;
}
