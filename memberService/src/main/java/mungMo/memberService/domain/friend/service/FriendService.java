package mungMo.memberService.domain.friend.service;

import mungMo.memberService.domain.friend.dto.FriendDTO;
import mungMo.memberService.domain.friend.entity.FriendEntity;
import mungMo.memberService.domain.friend.repository.FriendRepository;
import mungMo.memberService.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static mungMo.memberService.domain.friend.entity.FriendEntity.Of;

@Service
public class FriendService {
    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    public FriendService(MemberRepository memberRepository, FriendRepository friendRepository) {
        this.memberRepository = memberRepository;
        this.friendRepository = friendRepository;
    }

    @Transactional
    public void apply(Long fromFriendId, Long toFriendId) {
        friendRepository.save(Of(fromFriendId, memberRepository.findById(toFriendId), true));
        friendRepository.save(Of(toFriendId, memberRepository.findById(fromFriendId), false));
    }

    @Transactional
    public void accept(Long fromFriendId, Long toFriendId) {
        friendRepository.findByFromFriendIdAndMemberId(fromFriendId, toFriendId)
                .ifPresent(FriendEntity::accept);
    }

    @Transactional
    public void delete(Long fromFriendId, Long toFriendId) {
        // 내 친구 기록에서 친구 삭제
        friendRepository.findByFromFriendIdAndMemberId(fromFriendId, toFriendId)
                .ifPresent(entity -> friendRepository.deleteById(entity.getId()));
        // 친구의 친구 기록에서 나 삭제
        friendRepository.findByFromFriendIdAndMemberId(toFriendId, fromFriendId)
                .ifPresent(entity -> friendRepository.deleteById(entity.getId()));
    }

    @Transactional(readOnly = true)
    public List<FriendDTO> friendList(Long id) {
        return friendRepository.findByFromFriendId(id)
                .stream()
                .map(FriendEntity::changeToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
