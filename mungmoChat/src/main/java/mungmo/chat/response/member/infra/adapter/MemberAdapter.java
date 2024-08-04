package mungmo.chat.response.member.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.chat.response.member.entity.MemberEntity;
import mungmo.chat.response.member.infra.MemberRepository;
import mungmo.chat.response.member.infra.repository.SpringDataJpaMemberRepository;
import mungmo.chat.response.notification.external.ChatNotificationClient;
import mungmo.chat.response.notification.infra.repository.SpringDataJpaChatNotificationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberRepository {
    private final SpringDataJpaMemberRepository memberRepository;

    @Override
    public Optional<MemberEntity> findById(Long id) {
        return memberRepository.findById(id);
    }
}
