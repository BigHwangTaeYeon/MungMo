package mungMo.memberService.domain.town.entity;

import jakarta.persistence.*;
import lombok.Getter;
import mungMo.memberService.com.util.GetDate;
import mungMo.memberService.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "town")
@Getter
public class TownEntity {
    // memberId 참조
    @Id
    @Column(name = "town_id")
    private Long id;

    private String area;

    @Column(nullable = false)
    private boolean certification;

    @Column(name = "certification_date")
    private LocalDateTime certificationDate;

//    @JoinColumn(name = "member_id")
    @OneToOne(mappedBy = "town", cascade = CascadeType.ALL)
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
        return new TownEntity(id);
    }

    public TownEntity() {
    }

    public TownEntity(Long id) {
        certification = false;
        certificationDate = LocalDateTime.parse(GetDate.getCurrentTime("YYYYMMDDHHmmss"));
        this.id = id;
    }
}
