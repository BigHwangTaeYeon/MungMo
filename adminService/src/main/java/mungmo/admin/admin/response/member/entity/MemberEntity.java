package mungmo.admin.admin.response.member.entity;

import jakarta.persistence.*;
import lombok.*;
import mungmo.admin.admin.domain.embede.FileInfo;
import mungmo.admin.admin.response.member.external.MemberDto;

import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_login")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "oauthprovider")
    private SocialRoute oauthProvider;

    @OneToOne
    private FcmToken fcmToken;

    @Embedded
    private FileInfo fileInfo;

    private LocalDateTime create_date;

    private LocalDateTime recent_date;

    public MemberDto toDto() {
        return MemberDto.builder()
                .memberId(id)
                .email(email)
                .dogName(dogName)
                .dogLike(dogLike)
                .gender(gender)
                .ageRange(ageRange)
                .mannerTemperature(mannerTemperature)
                .authority(memberAuthority)
                .build();
    }
}
