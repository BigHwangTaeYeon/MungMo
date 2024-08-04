package mungMo.member.domain.town.service;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.town.entity.TownEntity;
import mungMo.member.domain.town.infra.TownRepository;
import mungMo.member.domain.town.infra.repository.SpringDataJpaTownRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TownService {
    private final TownRepository townRepository;

    @Transactional(readOnly = true)
    public TownEntity findEntity(Long id) {
        return townRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void register(MemberEntity member, String area) {
        member.getTown().certified(area);
    }

    @Transactional(readOnly = true)
    public void cancelCertification(TownEntity entity) {
        entity.expired();
    }

    @Transactional
    public void saveMemberEntity(MemberEntity member) {
        townRepository.save(new TownEntity(member));
    }
}
