package mungMo.memberService.domain.member.service;


import mungMo.memberService.domain.member.dto.MemberTypeDTO;
import mungMo.memberService.domain.member.entity.MemberTypeEntity;
import mungMo.memberService.domain.member.repository.MemberTypeRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberTypeService {

    private final MemberTypeRepository memberTypeRepository;

    public MemberTypeService(MemberTypeRepository memberTypeRepository) {
        this.memberTypeRepository = memberTypeRepository;
    }

    public List<MemberTypeDTO> userTypeList(Long memberId) {
        return memberTypeRepository.findByMemberId(memberId)
                .stream()
                .map(MemberTypeEntity::changeToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void userTypeResister(Long id, List<Integer> code) {
        resisterType(memberTypeRepository.findByMemberIdAndType(id, "MBTP"), code);
    }

    public void dogTypeResister(Long id, List<Integer> code) {
        resisterType(memberTypeRepository.findByMemberIdAndType(id, "DGTP"), code);
    }

    private void resisterType(LinkedList<MemberTypeEntity> dtoList, List<Integer> codeList) {

        for (Integer code : codeList) {
            dtoList.stream()
                    .filter(entity -> code == entity.getCode())
                    .forEach(MemberTypeEntity::useY);
            dtoList.stream()
                    .filter(entity -> code != entity.getCode())
                    .forEach(MemberTypeEntity::useN);
        }

    }
}
