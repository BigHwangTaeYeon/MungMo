package mungmo.admin.admin.response.member.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.com.filter.ObjectConverter;
import mungmo.admin.admin.response.member.entity.MemberEntity;
import mungmo.admin.admin.response.member.external.MemberClient;
import mungmo.admin.admin.response.member.infra.MemberRepository;
import mungmo.admin.admin.response.member.infra.repository.SpringDataJpaMemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberRepository {
    private final SpringDataJpaMemberRepository publicCodeRepository;
    private final MemberClient memberClient;

    @Override
    public MemberEntity findMemberById(Long id) {
        return publicCodeRepository.findById(id).orElseThrow();
    }

    @Override
    public MemberEntity getMemberByFeignClient(Long userId) {
        ResponseEntity<?> feignMember = memberClient.getMember(userId);
        return ObjectConverter.operating(feignMember, MemberEntity.class);
    }
}
