package mungMo.member.domain.friend.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FriendDto {
    private Long friend_id;

    private boolean accept;
}
