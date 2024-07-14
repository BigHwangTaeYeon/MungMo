package mungmo.mungmoChat.otherDomain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import mungmo.mungmoChat.otherDomain.member.dto.MemberDTO;
import org.apache.tomcat.jni.FileInfo;

import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_login")
@Getter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(name = "dog_name")
    private String dogName;

    @Column(name = "like_for_dog")
    private String dogLike;

    @Column(name = "manner_temperature", nullable = false)
    private int mannerTemperature;

    @Column(nullable = false)
    private String gender;

    @Column(name = "age_range", nullable = false)
    private String ageRange;

    @Enumerated(EnumType.STRING)
    @Column(name = "memberauthority")
    private MemberAuthority memberAuthority;

    @Embedded
    private FileInfo fileInfo;

    private LocalDateTime create_date;

    private LocalDateTime recent_date;

    public MemberEntity() {
    }

    public MemberDTO changeToDTO() {
        return MemberDTO.builder()
                .memberId(id)
                .email(email)
                .dogName(dogName)
                .dogLike(dogLike)
                .gender(gender)
                .ageRange(ageRange)
                .mannerTemperature(mannerTemperature)
                .build();
    }
}
