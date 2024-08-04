package mungMo.member.domain.friend.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.friend.entity.FriendEntity;
import mungMo.member.domain.friend.infra.FriendRepository;
import mungMo.member.domain.friend.infra.repository.SpringDataJpaFriendRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FriendAdapter implements FriendRepository {
    private final SpringDataJpaFriendRepository friendRepository;

    @Override
    public Optional<FriendEntity> findByMemberFromIdAndMemberToId(Long memberFromId, Long memberToId) {
        return friendRepository.findByMemberFromIdAndMemberToId(memberFromId, memberToId);
    }

    @Override
    public List<FriendEntity> findFriendList(Long id) {
        return friendRepository.findFriendList(id);
    }

    @Override
    public void save(FriendEntity of) {
        friendRepository.save(of);
    }

    @Override
    public void deleteById(Long id) {
        friendRepository.deleteById(id);
    }
}
