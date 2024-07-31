package mungmo.adminService.admin.otherDomain.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mungmo.adminService.admin.com.filter.ObjectConverter;
import mungmo.adminService.admin.otherDomain.member.dto.MemberDTO;
import mungmo.adminService.admin.otherDomain.member.entity.MemberEntity;
import mungmo.adminService.admin.otherDomain.member.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberServiceClient memberServiceClient;

    public MemberService(MemberRepository memberRepository, MemberServiceClient memberServiceClient) {
        this.memberRepository = memberRepository;
        this.memberServiceClient = memberServiceClient;
    }

    @Transactional(readOnly = true)
    public MemberEntity findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    @Transactional(readOnly = true)
    public MemberDTO getMemberByFeignClient(Long userId) {
        ResponseEntity<?> feignMember = memberServiceClient.getMember(userId);
//        ObjectMapper mapper = new ObjectMapper();

//        ObjectConverter converter = new ObjectConverter();
        return ObjectConverter.operating(feignMember, MemberDTO.class);

//        return mapper.convertValue(ObjectConverter.jsonBody(feignMember), MemberDTO.class);
    }
}
