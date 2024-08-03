package mungMo.member.domain.town.facade;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.service.MemberService;
import mungMo.member.domain.town.entity.TownEntity;
import mungMo.member.domain.town.service.TownService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TownFacade {
    private final TownService townService;
    private final MemberService memberService;

    @Transactional
    public void register(String area, Long id) throws PreconditionFailedException {
        MemberEntity member = memberService.infoById(id);
        townService.register(member, area);
    }

    @Transactional(readOnly = true)
    public void cancelCertification(Long id) {
        TownEntity entity = townService.findEntity(id);
        townService.cancelCertification(entity);
    }
}
