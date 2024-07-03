package mungMo.memberService.domain.member.service;


import mungMo.memberService.domain.member.dto.MemberTypeDTO;
import mungMo.memberService.domain.member.entity.MemberTypeEntity;
import mungMo.memberService.domain.member.repository.MemberTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberTypeService {

    private final MemberTypeRepository memberTypeRepository;

    public MemberTypeService(MemberTypeRepository memberTypeRepository) {
        this.memberTypeRepository = memberTypeRepository;
    }

    @Transactional(readOnly = true)
    public List<MemberTypeDTO> userTypeList(Long memberId) {
        return memberTypeRepository.findByMemberId(memberId)
                .stream()
                .map(MemberTypeEntity::changeToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public void userTypeResister(Long id, List<Integer> code) {
        resisterType(memberTypeRepository.findByMemberIdAndPublicCodeCodeType(id, "MBTP"), code);
    }

    @Transactional
    public void dogTypeResister(Long id, List<Integer> code) {
        resisterType(memberTypeRepository.findByMemberIdAndPublicCodeCodeType(id, "DGTP"), code);
    }

    private void resisterType(LinkedList<MemberTypeEntity> dtoList, List<Integer> codeList) {
        dtoList.forEach(MemberTypeEntity::useN);

        for (Integer code : codeList) {
            dtoList.stream()
                    .filter(entity -> code == entity.getPublicCode().getCode())
                    .forEach(MemberTypeEntity::useY);
        }
    }
}
