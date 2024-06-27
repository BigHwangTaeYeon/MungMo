package mungMo.memberService.domain.friend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mungMo.memberService.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
public class FriendDTO {
    private Long friend_id;

    private boolean accept;
}
