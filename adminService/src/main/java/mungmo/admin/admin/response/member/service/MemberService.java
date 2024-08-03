package mungmo.admin.admin.response.member.service;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.com.filter.ObjectConverter;
import mungmo.admin.admin.response.member.external.MemberClient;
import mungmo.admin.admin.response.member.entity.MemberEntity;
import mungmo.admin.admin.response.member.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberClient memberServiceClient;

    @Transactional(readOnly = true)
    public MemberEntity findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    @Transactional(readOnly = true)
    public MemberEntity getMemberByFeignClient(Long userId) {
        ResponseEntity<?> feignMember = memberServiceClient.getMember(userId);
        return ObjectConverter.operating(feignMember, MemberEntity.class);
    }
}
