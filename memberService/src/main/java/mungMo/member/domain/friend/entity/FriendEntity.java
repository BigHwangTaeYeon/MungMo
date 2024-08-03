package mungMo.member.domain.friend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mungMo.member.domain.friend.external.FriendDto;
import mungMo.member.domain.member.entity.MemberEntity;

@Entity
@Table(name = "friends")
@NoArgsConstructor
public class FriendEntity {
    @Id @GeneratedValue
    @Column(name = "friends_id")
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_friend_id", nullable = false)
    private MemberEntity memberFrom;

    @ManyToOne
    @JoinColumn(name = "to_friend_id", nullable = false)
    private MemberEntity memberTo;

    @Column(nullable = false)
    private boolean accept;

    public void accept() {
        this.accept = true;
    }

    public static FriendEntity of(MemberEntity memberFrom, MemberEntity memberTo) {
        return new FriendEntity(memberFrom, memberTo);
    }

    public static FriendEntity of(MemberEntity memberFrom, MemberEntity memberTo, boolean accept) {
        return new FriendEntity(memberFrom, memberTo, accept);
    }

    private FriendEntity(MemberEntity memberFrom, MemberEntity memberTo) {
        this.memberFrom = memberFrom;
        this.memberTo = memberTo;
    }

    private FriendEntity(MemberEntity memberFrom, MemberEntity memberTo, boolean accept) {
        this.memberFrom = memberFrom;
        this.memberTo = memberTo;
        this.accept = accept;
    }

    public static FriendDto toDto(FriendEntity entity) {
        return FriendDto.builder()
                .friend_id(entity.memberTo.getId())
                .accept(entity.accept)
                .build();
    }

}
