package mungMo.member.domain.report.infra;

import mungMo.member.domain.report.entity.ReportEntity;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    List<ReportEntity> findByFromMemberId(Long userId);

    Optional<ReportEntity> findById(long id);

    void save(ReportEntity entity);
}
