package mungMo.member.domain.town.infra;

import mungMo.member.domain.town.entity.TownEntity;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TownRepository {
    Optional<List<TownEntity>> findByCertificationDateBefore(LocalDateTime localDateTime);

    void bulkCertificationFalse(@Param("startDate") LocalDateTime start, @Param("endDate") LocalDateTime end);

    void bulkCertificationFalseToDay(@Param("day") LocalDateTime day);

    Optional<TownEntity> findById(Long id);

    void save(TownEntity townEntity);
}
