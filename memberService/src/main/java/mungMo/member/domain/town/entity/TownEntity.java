package mungMo.member.domain.town.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mungMo.member.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "town")
@Getter
@NoArgsConstructor
public class TownEntity {
    // memberId 참조
    @Id @GeneratedValue
    @Column(name = "town_id")
    private Long id;

    private String area;

    @Column(nullable = false)
    private boolean certification;

    @Column(name = "certification_date")
    private LocalDateTime certificationDate;

    @JoinColumn(name = "member_id")
    @OneToOne(cascade = CascadeType.ALL)
    private MemberEntity member;

    public void certified(String area) {
        this.area = area;
        certification = true;
        certificationDate = LocalDateTime.now();
    }

    public void expired() {
        certification = false;
    }

    public TownEntity(MemberEntity member) {
        certification = false;
        this.member = member;
        certificationDate = LocalDateTime.now();
    }
}
