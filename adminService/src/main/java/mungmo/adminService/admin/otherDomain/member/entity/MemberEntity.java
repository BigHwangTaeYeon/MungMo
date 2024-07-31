package mungmo.adminService.admin.otherDomain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mungmo.adminService.admin.domain.embede.FileInfo;
import mungmo.adminService.admin.otherDomain.member.dto.FcmTokenDTO;
import mungmo.adminService.admin.otherDomain.member.dto.MemberDTO;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_login")
@Getter @Setter
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

    public MemberEntity() {
    }

    public MemberEntity(Long id, String email, String dogName, String dogLike, int mannerTemperature, String gender, String ageRange, MemberAuthority memberAuthority, SocialRoute oauthProvider, FcmToken fcmToken, FileInfo fileInfo, LocalDateTime create_date, LocalDateTime recent_date) {
        this.id = id;
        this.email = email;
        this.dogName = dogName;
        this.dogLike = dogLike;
        this.mannerTemperature = mannerTemperature;
        this.gender = gender;
        this.ageRange = ageRange;
        this.memberAuthority = memberAuthority;
        this.oauthProvider = oauthProvider;
        this.fcmToken = fcmToken;
        this.fileInfo = fileInfo;
        this.create_date = create_date;
        this.recent_date = recent_date;
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
                .authority(memberAuthority)
//                .dogImgName(fileInfo.getMask_name())
//                .dogImgUrl(fileInfo.getFile_path())
//                .fcmTokenDTO(
//                        StringUtils.hasText(fcmToken.getFcmToken())
//                                ? new FcmTokenDTO(fcmToken.getFcmToken())
//                                : new FcmTokenDTO()
//                )
                .build();
    }
}
