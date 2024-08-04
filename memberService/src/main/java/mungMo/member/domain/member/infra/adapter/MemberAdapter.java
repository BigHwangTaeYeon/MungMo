package mungMo.member.domain.member.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.infra.MemberRepository;
import mungMo.member.domain.member.infra.repository.SpringDataJpaMemberRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberRepository {
    private final SpringDataJpaMemberRepository memberRepository;

    @Override
    public MemberEntity findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public void save(MemberEntity member) {
        memberRepository.save(member);
    }

    @Override
    public Optional<MemberEntity> findById(Long id) {
        return memberRepository.findById(id);
    }
}
