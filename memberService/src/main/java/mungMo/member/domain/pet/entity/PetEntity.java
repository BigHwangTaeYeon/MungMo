package mungMo.member.domain.pet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mungMo.member.domain.embede.FileInfo;
import mungMo.member.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "pet")
@Getter
@NoArgsConstructor
public class PetEntity {
    // memberId 참조
    @Id @GeneratedValue
    @Column(name = "pet_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")
    private String gender;

    @Column(name = "use")
    private boolean use;

    @Embedded
    private FileInfo fileInfo;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;
}
