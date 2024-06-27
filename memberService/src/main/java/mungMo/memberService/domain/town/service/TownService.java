package mungMo.memberService.domain.town.service;

import mungMo.memberService.domain.member.entity.MemberEntity;
import mungMo.memberService.domain.member.repository.MemberRepository;
import mungMo.memberService.domain.town.repository.TownRepository;
import org.springframework.stereotype.Service;

@Service
public class TownService {
    private final MemberRepository memberRepository;
    private final TownRepository townRepository;

    public TownService(MemberRepository memberRepository, TownRepository townRepository) {
        this.memberRepository = memberRepository;
        this.townRepository = townRepository;
    }

    public void register(String area, Long id) {
        memberRepository.findById(id)
                .map(MemberEntity::getTown)
                .ifPresent(town ->
                        {
                            town.certified(area);
                            townRepository.save(town);
                        }
                );
    }
}
