package mungMo.member.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mungMo.member.domain.embede.FileInfo;
import mungMo.member.domain.member.external.DogImgDto;
import mungMo.member.domain.member.external.MemberDto;
import mungMo.member.domain.member.external.MemberIdAndDogNameDto;
import mungMo.member.domain.member.entity.function.DIMannerTemperature;
import mungMo.member.domain.member.fcm.domain.FcmToken;
import mungMo.member.domain.town.entity.TownEntity;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_login")
@Getter
@NoArgsConstructor
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
    @ColumnDefault("30")
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

    @OneToOne(mappedBy = "member")
    private TownEntity town;

    @Transient  // 데이터베이스에 저장할 필요 없음
    private DIMannerTemperature diMannerTemperature;

    public MemberEntity(DIMannerTemperature diMannerTemperature) {
        this.diMannerTemperature = diMannerTemperature;
    }

    public void changeDogName(String dogName) {
        this.dogName = dogName;
    }

    public void changeDogLike(String dogLike) {
        this.dogLike = dogLike;
    }

    public void updateRecentDate() {
        recent_date = LocalDateTime.now();
    }

    public void fileInfoInstance(FileInfo info) {
        fileInfo = info;
    }

    @Builder
    public MemberEntity(String email, String gender, String ageRange, SocialRoute oAuthProvider) {
        create_date = LocalDateTime.now();
        this.mannerTemperature = 30;
        this.email = email;
        this.gender = gender;
        this.ageRange = ageRange;
        this.memberAuthority = MemberAuthority.ROLE_MEMBER;
        this.oauthProvider = oAuthProvider;
    }

    public MemberIdAndDogNameDto toDogDTO() {
        return MemberIdAndDogNameDto.builder()
                .id(id)
                .dogName(dogName)
                .build();
    }

    public MemberDto toDto() {
        return MemberDto.builder()
                .memberId(id)
                .email(email)
                .dogName(dogName)
                .dogLike(dogLike)
                .gender(gender)
                .ageRange(ageRange)
                .mannerTemperature(mannerTemperature)
                .townCertificated(town.isCertification())
                .authority(memberAuthority)
                .build();
    }

    public DogImgDto dogImgDTO() {
        return DogImgDto.builder()
                .filePath(fileInfo.getFilePath())
                .originalName(fileInfo.getOriginalName())
                .maskName(fileInfo.getMaskName())
                .fileType(fileInfo.getFileType())
                .build();
    }

    public void comeFromReport(String problem) {
        diMannerTemperature.mtfFactory(this).comeFromReport(problem);
    }

    public void comeFromReview(int point) {
        diMannerTemperature.mtfFactory(this).comeFromReview(point);
    }

    public void comeFromNoShow() {
        diMannerTemperature.mtfFactory(this).comeFromNoShow();
    }

    public void fromPolicy(int point) {
        this.mannerTemperature = point;
    }
}
