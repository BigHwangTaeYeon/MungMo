package mungMo.member.domain.friend.service;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.friend.entity.FriendEntity;
import mungMo.member.domain.friend.infra.FriendRepository;
import mungMo.member.domain.friend.infra.repository.SpringDataJpaFriendRepository;
import mungMo.member.domain.member.entity.MemberEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static mungMo.member.domain.friend.entity.FriendEntity.of;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    @Transactional(readOnly = true)
    public FriendEntity findFriend(Long fromFriendId, Long toFriendId) {
        return friendRepository.findByMemberFromIdAndMemberToId(fromFriendId, toFriendId).orElseThrow();
    }

    @Transactional
    public void apply(FriendEntity friendFrom, FriendEntity friendTo, MemberEntity memberFrom, MemberEntity memberTo) {
        if(!ObjectUtils.isEmpty(friendFrom) || !ObjectUtils.isEmpty(friendTo)) {
            throw new IllegalStateException("이미 친구입니다.");
        }

        friendRepository.save(of(memberFrom, memberTo, true));
        friendRepository.save(of(memberTo, memberFrom, false));
    }

    @Transactional
    public void accept(Long fromFriendId, Long toFriendId) {
        friendRepository.findByMemberFromIdAndMemberToId(fromFriendId, toFriendId)
                .ifPresent(FriendEntity::accept);
    }

    @Transactional
    public void delete(Long fromFriendId, Long toFriendId) {
        // 내 친구 기록에서 친구 삭제
        friendRepository.findByMemberFromIdAndMemberToId(fromFriendId, toFriendId)
                .ifPresent(entity -> friendRepository.deleteById(entity.getId()));
        // 친구의 친구 기록에서 나 삭제
        friendRepository.findByMemberFromIdAndMemberToId(toFriendId, fromFriendId)
                .ifPresent(entity -> friendRepository.deleteById(entity.getId()));
    }

    @Transactional(readOnly = true)
    public List<FriendEntity> friendList(Long id) {
        return friendRepository.findFriendList(id);
    }
}
