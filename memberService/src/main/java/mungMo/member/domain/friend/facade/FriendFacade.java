package mungMo.member.domain.friend.facade;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.domain.friend.entity.FriendEntity;
import mungMo.member.domain.friend.external.FriendDto;
import mungMo.member.domain.friend.service.FriendService;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendFacade {
    private final MemberService memberApiService;
    private final FriendService friendService;

    @Transactional
    public void apply(Long fromFriendId, Long toFriendId) throws PreconditionFailedException {
        FriendEntity friendFrom = friendService.findFriend(fromFriendId, toFriendId);
        FriendEntity friendTo = friendService.findFriend(toFriendId, fromFriendId);

        MemberEntity memberFrom = memberApiService.infoById(fromFriendId);
        MemberEntity memberTo = memberApiService.infoById(toFriendId);

        friendService.apply(friendFrom, friendTo, memberFrom, memberTo);
    }

    @Transactional
    public void accept(Long fromFriendId, Long toFriendId) {
        friendService.accept(fromFriendId, toFriendId);
    }

    @Transactional
    public void delete(Long fromFriendId, Long toFriendId) {
        friendService.delete(fromFriendId, toFriendId);
    }

    @Transactional(readOnly = true)
    public List<FriendDto> friendList(Long id) {
        return friendService.friendList(id)
                .stream()
                .map(FriendEntity::toDto)
                .collect(Collectors.toList());
    }
}
