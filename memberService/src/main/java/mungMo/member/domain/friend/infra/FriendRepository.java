package mungMo.member.domain.friend.infra;

import mungMo.member.domain.friend.entity.FriendEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository {
    Optional<FriendEntity> findByMemberFromIdAndMemberToId(Long memberFromId, Long memberToId);

    List<FriendEntity> findFriendList(@Param("id") Long id);

    void save(FriendEntity of);

    void deleteById(Long id);
}
