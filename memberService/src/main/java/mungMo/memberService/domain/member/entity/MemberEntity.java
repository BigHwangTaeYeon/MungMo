package mungMo.memberService.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import mungMo.memberService.com.util.GetDate;
import mungMo.memberService.domain.embede.FileInfo;
import mungMo.memberService.domain.member.dto.MemberDTO;
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

    private String nickname;

    @Column(name = "manner_temperature", nullable = false)
    private int mannerTemperature;

    @Column(nullable = false)
    private String gender;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id")
    private TownEntity town;

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateRecentDate() {
        recent_date = LocalDateTime.parse(GetDate.getCurrentTime("YYYYMMDDHHmmss"));
    }

    public void fileInfoInstance(FileInfo info) {
        fileInfo = info;
    }

    public MemberEntity() {
    }

    @Builder
    public MemberEntity(String email, String nickname, SocialRoute oAuthProvider) {
        create_date = LocalDateTime.parse(GetDate.getCurrentTime("YYYYMMDDHHmmss"));
        this.mannerTemperature = 30;
        this.email = email;
        this.nickname = nickname;
        this.memberAuthority = MemberAuthority.ROLE_MEMBER;
        this.oauthProvider = oAuthProvider;
    }

    public MemberDTO changeToDTO() {
        return MemberDTO.builder()
                .memberId(id)
                .email(email)
                .nickName(nickname)
                .gender(gender)
                .mannerTemperature(mannerTemperature)
                .townCertificated(town.isCertification())
                .original_name(fileInfo.getOriginal_name())
                .mask_name(fileInfo.getMask_name())
                .file_path(fileInfo.getFile_path())
                .build();
    }
}
