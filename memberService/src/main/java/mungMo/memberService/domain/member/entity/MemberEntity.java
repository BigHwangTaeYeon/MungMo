package mungMo.memberService.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import mungMo.memberService.com.util.GetDate;
import mungMo.memberService.domain.embede.FileInfo;
import mungMo.memberService.domain.member.dto.DogImgDTO;
import mungMo.memberService.domain.member.dto.MemberDTO;
import mungMo.memberService.domain.member.dto.MemberIdAndDogNameDTO;
import mungMo.memberService.domain.town.entity.TownEntity;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "oauthprovider")
    private SocialRoute oauthProvider;

    @Embedded
    private FileInfo fileInfo;

    private LocalDateTime create_date;

    private LocalDateTime recent_date;

    @OneToOne(mappedBy = "member")
    private TownEntity town;

    public void changeDogName(String dogName) {
        this.dogName = dogName;
    }

    public void changeDogLike(String dogLike) {
        this.dogLike = dogLike;
    }

    public void updateRecentDate() {
        recent_date = GetDate.pareLocalDataTime("yyyyMMddHHmmss");
    }

    public void fileInfoInstance(FileInfo info) {
        fileInfo = info;
    }

    public MemberEntity() {
    }

    @Builder
    public MemberEntity(String email, String gender, String ageRange, SocialRoute oAuthProvider, TownEntity town) {
        create_date = GetDate.pareLocalDataTime("yyyyMMddHHmmss");
        this.mannerTemperature = 30;
        this.email = email;
        this.gender = gender;
        this.ageRange = ageRange;
        this.memberAuthority = MemberAuthority.ROLE_MEMBER;
        this.oauthProvider = oAuthProvider;
    }

    public MemberIdAndDogNameDTO changeToDogDTO() {
        return MemberIdAndDogNameDTO.builder()
                .id(id)
                .dogName(dogName)
                .build();
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
                .townCertificated(town.isCertification())
                .build();
    }

    public DogImgDTO dogImgDTO() {
        return DogImgDTO.builder()
                .file_path(fileInfo.getFile_path())
                .original_name(fileInfo.getOriginal_name())
                .mask_name(fileInfo.getMask_name())
                .file_type(fileInfo.getFile_type())
                .build();
    }
}
