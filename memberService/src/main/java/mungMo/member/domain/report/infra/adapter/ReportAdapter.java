package mungMo.member.domain.report.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.friend.entity.FriendEntity;
import mungMo.member.domain.friend.infra.repository.SpringDataJpaFriendRepository;
import mungMo.member.domain.report.entity.ReportEntity;
import mungMo.member.domain.report.infra.ReportRepository;
import mungMo.member.domain.report.infra.repository.SpringDataJpaReportRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReportAdapter implements ReportRepository {
    private final SpringDataJpaReportRepository reportRepository;

    @Override
    public List<ReportEntity> findByFromMemberId(Long userId) {
        return reportRepository.findByFromMemberId(userId);
    }

    @Override
    public Optional<ReportEntity> findById(long id) {
        return reportRepository.findById(id);
    }

    @Override
    public void save(ReportEntity entity) {
        reportRepository.save(entity);
    }
}
