package mungMo.memberService.domain.town.repository;

import mungMo.memberService.domain.town.entity.TownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<TownEntity, Long> {
    List<TownEntity> findByCertificationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<TownEntity> findByCertificationAndCertificationDateBetween(boolean Certification, LocalDateTime startDate, LocalDateTime endDate);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE town t SET t.certification = false WHERE t.certification = true  AND t.certification_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    void bulkCertificationFalse(@Param("startDate") LocalDateTime start, @Param("endDate") LocalDateTime end);
}
