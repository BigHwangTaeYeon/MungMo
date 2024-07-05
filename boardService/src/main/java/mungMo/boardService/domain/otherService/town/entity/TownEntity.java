package mungMo.boardService.domain.otherService.town.entity;

import jakarta.persistence.*;
import lombok.Getter;
import mungMo.boardService.domain.otherService.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_town")
@Getter
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

//    @JoinColumn(name = "member_id")
    @OneToOne(mappedBy = "town", cascade = CascadeType.ALL)
    private MemberEntity member;

    public TownEntity() {
    }
}
