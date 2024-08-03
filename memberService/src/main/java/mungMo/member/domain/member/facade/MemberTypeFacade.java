package mungMo.member.domain.member.facade;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.entity.MemberTypeEntity;
import mungMo.member.domain.member.external.MemberTypeDto;
import mungMo.member.domain.member.service.MemberTypeService;
import mungMo.member.response.publiccode.entity.PublicCodeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static mungMo.member.response.publiccode.entity.PublicType.DGTP;
import static mungMo.member.response.publiccode.entity.PublicType.MBTP;

@Service
@RequiredArgsConstructor
public class MemberTypeFacade {
    private final MemberTypeService memberTypeService;

    @Transactional(readOnly = true)
    public List<MemberTypeDto> userTypeList(Long memberId) {
        return memberTypeService.userTypeList(memberId)
                .stream()
                .map(MemberTypeEntity::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void userTypeResister(Long id, List<Integer> code) {
        memberTypeService.resisterType(id, MBTP, code);
    }

    @Transactional
    public void dogTypeResister(Long id, List<Integer> code) {
        memberTypeService.resisterType(id, DGTP, code);
    }
}
