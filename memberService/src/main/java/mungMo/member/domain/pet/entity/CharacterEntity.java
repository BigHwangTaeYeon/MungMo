package mungMo.member.domain.pet.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

// characteristic 강아지 특징
@Entity
@Table(name = "pet_characteristic")
@NoArgsConstructor
public class CharacterEntity {
    @Id @GeneratedValue
    @Column(name = "character_id")
    private Long id;

    // 입마개
    @Column(name = "muzzle")
    private boolean muzzle;

    // 짖음
    @Column(name = "barking")
    private boolean barking;

    // 마운팅
    @Column(name = "mounting")
    private boolean mounting;

    // 활발함
    @Column(name = "lively")
    private boolean lively;

    // 소심함
    @Column(name = "timid")
    private boolean timid;

    // 예민함
    @Column(name = "sensitivity")
    private boolean sensitivity;

    // 무던함, 성격이 너그럽고 까다롭지 않다.
    @Column(name = "easygoing")
    private boolean easygoing;

    // 호기심
    @Column(name = "curiosity")
    private boolean curiosity;

    // 신중함
    @Column(name = "deliberation")
    private boolean deliberation;

    // 소개 글
    @Column(name = "introduce")
    private String intro;

    @OneToOne
    private PetEntity pet;
}
