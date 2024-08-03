package mungMo.member.domain.report.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import mungMo.member.com.exception.FileUploadException;
import mungMo.member.com.util.Upload;
import mungMo.member.domain.embede.FileInfo;
import mungMo.member.domain.report.external.ReportDto;
import mungMo.member.domain.report.entity.ReportEntity;
import mungMo.member.domain.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    @Value("${api.upload.dir.report}")
    private String uploadDir;

    @Transactional(readOnly = true)
    public List<ReportEntity> userReportList(long userId) {
        return reportRepository.findByFromMemberId(userId);
    }

    @Transactional(readOnly = true)
    public ReportEntity reportDetail(long id) {
        return reportRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("존재하지 않는 데이터입니다."));
    }

    @Transactional
    public void register(ReportDto reportDTO, MultipartFile multipartFile) throws FileUploadException {
        if(!ObjectUtils.isEmpty(multipartFile)) {
            Map<String, String> upload = new Upload(uploadDir, multipartFile).uploadImage();
            FileInfo file = new FileInfo(upload, "img");
            ReportEntity entity = new ReportEntity(file, reportDTO);

            reportRepository.save(entity);
        }
    }
}
