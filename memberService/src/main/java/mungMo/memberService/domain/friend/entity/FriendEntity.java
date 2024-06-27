package mungMo.memberService.domain.friend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import mungMo.memberService.domain.friend.dto.FriendDTO;
import mungMo.memberService.domain.member.entity.MemberEntity;

import java.util.Optional;

@Entity
@Getter
@Table(name = "friends")
public class FriendEntity {
    @Id @GeneratedValue
    @Column(name = "friends_id")
    private Long id;

    @Column(name = "from_friend_id", nullable = false)
    private Long fromFriendId;

    @ManyToOne
    @JoinColumn(name = "to_friend_id")
    private MemberEntity member;

    @Column(nullable = false)
    private boolean accept;

    public void accept() {
        this.accept = true;
    }

    public static FriendEntity Of(Long myId, Optional<MemberEntity> member, boolean accept) {
        return new FriendEntity(myId, member, accept);
    }

    public FriendEntity() {
    }

    public FriendEntity(Long myId, Optional<MemberEntity> member, boolean accept) {
        this.fromFriendId = myId;
        this.member = member.orElseThrow(IllegalArgumentException::new);
        this.accept = accept;
    }

    public static FriendDTO changeToDTO(FriendEntity entity) {
        return FriendDTO.builder()
                .friend_id(entity.member.getId())
                .accept(entity.accept)
                .build();
    }

}
