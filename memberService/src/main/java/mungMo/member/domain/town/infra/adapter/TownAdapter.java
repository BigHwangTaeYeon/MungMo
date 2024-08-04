package mungMo.member.domain.town.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.report.infra.repository.SpringDataJpaReportRepository;
import mungMo.member.domain.town.entity.TownEntity;
import mungMo.member.domain.town.infra.TownRepository;
import mungMo.member.domain.town.infra.repository.SpringDataJpaTownRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TownAdapter implements TownRepository {
    private final SpringDataJpaTownRepository townRepository;

    @Override
    public Optional<List<TownEntity>> findByCertificationDateBefore(LocalDateTime localDateTime) {
        return townRepository.findByCertificationDateBefore(localDateTime);
    }

    @Override
    public void bulkCertificationFalse(LocalDateTime start, LocalDateTime end) {
        townRepository.bulkCertificationFalse(start, end);
    }

    @Override
    public void bulkCertificationFalseToDay(LocalDateTime day) {
        townRepository.bulkCertificationFalseToDay(day);
    }

    @Override
    public Optional<TownEntity> findById(Long id) {
        return townRepository.findById(id);
    }

    @Override
    public void save(TownEntity townEntity) {
        townRepository.save(townEntity);
    }
}
