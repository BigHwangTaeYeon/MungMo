package mungMo.member.domain.report.facade;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.exception.FileUploadException;
import mungMo.member.domain.report.entity.ReportEntity;
import mungMo.member.domain.report.external.ReportDto;
import mungMo.member.domain.report.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportFacade {
    private final ReportService reportService;

    @Transactional(readOnly = true)
    public List<ReportDto> userReportList(long userId) {
        return reportService.userReportList(userId)
                .stream()
                .map(ReportEntity::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReportDto reportDetail(long id) {
        return reportService.reportDetail(id).toDto();
    }

    @Transactional
    public void register(ReportDto reportDTO, MultipartFile multipartFile) throws FileUploadException {
        reportService.register(reportDTO, multipartFile);
    }
}
