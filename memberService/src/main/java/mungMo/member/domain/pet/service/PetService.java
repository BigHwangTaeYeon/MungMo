package mungMo.member.domain.pet.service;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.pet.entity.PetEntity;
import mungMo.member.domain.pet.infra.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public void findEntity(Long id) {
    }
}
