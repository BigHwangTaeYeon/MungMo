package mungMo.memberService.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import mungMo.memberService.com.util.GetDate;
import mungMo.memberService.domain.embede.FileInfo;
import mungMo.memberService.domain.member.dto.SocialRoute;
import mungMo.memberService.domain.member.town.entity.TownEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_login")
@Getter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    private String nickname;

    @Column(name = "manner_temperature")
    private int mannerTemperature;

    @Column(nullable = false)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauthprovider")
    private SocialRoute oauthProvider;

    @Embedded
    private FileInfo fileInfo;

    private LocalDateTime create_date;

    private LocalDateTime recent_date;

    @OneToOne
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
        this.email = email;
        this.nickname = nickname;
        this.oauthProvider = oAuthProvider;
    }
}
