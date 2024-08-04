package mungMo.member.domain.member.service;


import lombok.RequiredArgsConstructor;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.entity.MemberTypeEntity;
import mungMo.member.domain.member.infra.MemberTypeRepository;
import mungMo.member.domain.member.infra.repository.SpringDataJpaMemberTypeRepository;
import mungMo.member.response.publiccode.entity.PublicCodeEntity;
import mungMo.member.response.publiccode.entity.PublicType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberTypeService {
    private final MemberTypeRepository memberTypeRepository;

    @Transactional(readOnly = true)
    public List<MemberTypeEntity> userTypeList(Long memberId) {
        return memberTypeRepository.findByMemberId(memberId);
    }

    @Transactional
    public void resisterType(Long id, PublicType type, List<Integer> codeList) {
        LinkedList<MemberTypeEntity> dtoList = memberTypeRepository.findByMemberIdAndPublicCodeCodeType(id, PublicType.str(type));

        dtoList.forEach(MemberTypeEntity::useN);

        for (Integer code : codeList) {
            dtoList.stream()
                    .filter(entity -> code == entity.getPublicCode().getCode())
                    .forEach(MemberTypeEntity::useY);
        }
    }

    @Transactional
    public void save(List<PublicCodeEntity> pcEntity, MemberEntity member) {
        for (PublicCodeEntity entity : pcEntity) {
            memberTypeRepository.save(entity.toMemberType(entity, member));
        }
    }
}
