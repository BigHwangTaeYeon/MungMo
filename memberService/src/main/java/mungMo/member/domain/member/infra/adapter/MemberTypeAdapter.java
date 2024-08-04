package mungMo.member.domain.member.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.member.entity.MemberTypeEntity;
import mungMo.member.domain.member.infra.MemberTypeRepository;
import mungMo.member.domain.member.infra.repository.SpringDataJpaMemberTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberTypeAdapter implements MemberTypeRepository {
    private final SpringDataJpaMemberTypeRepository memberTypeRepository;

    @Override
    public List<MemberTypeEntity> findByMemberId(Long memberId) {
        return memberTypeRepository.findByMemberId(memberId);
    }

    @Override
    public LinkedList<MemberTypeEntity> findByMemberIdAndPublicCodeCodeType(Long id, String type) {
        return memberTypeRepository.findByMemberIdAndPublicCodeCodeType(id, type);
    }

    @Override
    public void save(MemberTypeEntity memberType) {
        memberTypeRepository.save(memberType);
    }
}
