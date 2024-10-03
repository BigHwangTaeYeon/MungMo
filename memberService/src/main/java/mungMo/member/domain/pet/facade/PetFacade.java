package mungMo.member.domain.pet.facade;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.service.MemberService;
import mungMo.member.domain.pet.entity.PetEntity;
import mungMo.member.domain.pet.service.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetFacade {
    private final PetService petService;

}
