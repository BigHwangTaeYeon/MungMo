package mungMo.memberService.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import mungMo.memberService.com.util.getDate;
import mungMo.memberService.domain.member.dto.SocialRoute;

import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_login")
@Getter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    @Column(name = "utteok_nickname")
    private String utteokNickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauthprovider")
    private SocialRoute oauthProvider;

    private LocalDateTime create_date;

    private LocalDateTime recent_date;

    public void changeUtteok_nickname(String utteok_nickname) {
        this.utteokNickname = utteok_nickname;
    }

    public void createUser() {
        create_date = LocalDateTime.parse(getDate.getCurrentTime("YYYYMMDDHHmmss"));
    }

    public void connectUser() {
        recent_date = LocalDateTime.parse(getDate.getCurrentTime("YYYYMMDDHHmmss"));
    }

    public MemberEntity() {
    }

    @Builder
    public MemberEntity(String email, String nickname, SocialRoute oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oauthProvider = oAuthProvider;
    }
}
