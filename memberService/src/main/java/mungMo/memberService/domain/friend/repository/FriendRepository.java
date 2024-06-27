package mungMo.memberService.domain.friend.repository;

import mungMo.memberService.domain.friend.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    Optional<FriendEntity> findByFromFriendIdAndMemberId(Long myId, Long friendId);

    List<FriendEntity> findByFromFriendId(Long id);
}
