package mungMo.member.domain.pet.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.pet.entity.PetEntity;
import mungMo.member.domain.pet.infra.PetRepository;
import mungMo.member.domain.pet.infra.repository.SpringDataJpaPetRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PetAdapter implements PetRepository {
    private final SpringDataJpaPetRepository petRepository;

}
