package mungMo.memberService.domain.member.town.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import mungMo.memberService.com.util.GetDate;
import mungMo.memberService.domain.embede.FileInfo;
import mungMo.memberService.domain.member.dto.SocialRoute;
import mungMo.memberService.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "town")
@Getter
public class TownEntity {
    // memberId 참조
    @Id
    private Long id;

    private String area;

    @Column(nullable = false)
    private static boolean certification;

    @Column(name = "certification_date")
    private static LocalDateTime certificationDate;

    @JoinColumn(name = "member_id")
    @OneToOne(mappedBy = "town")
    private MemberEntity member;

    public void certified(String area) {
        this.area = area;
        certification = true;
        certificationDate = LocalDateTime.parse(GetDate.getCurrentTime("YYYYMMDDHHmmss"));
    }

    public void expired() {
        certification = true;
    }

    public static TownEntity firstCreateInstance(long id) {
        certification = false;
        certificationDate = LocalDateTime.parse(GetDate.getCurrentTime("YYYYMMDDHHmmss"));
        return new TownEntity(id);
    }

    public TownEntity() {
    }

    public TownEntity(Long id) {
        this.id = id;
    }
}
