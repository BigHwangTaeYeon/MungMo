package mungMo.member.domain.report.infra.repository;

import mungMo.member.domain.report.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaReportRepository extends JpaRepository<ReportEntity, Long> {
    List<ReportEntity> findByFromMemberId(Long userId);
}
