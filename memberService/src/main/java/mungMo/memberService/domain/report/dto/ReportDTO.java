package mungMo.memberService.domain.report.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mungMo.memberService.domain.embede.FileInfo;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReportDTO {
    private String title;
    private String content;

    private Long fromId;
    private Long toId;

    private boolean status;

    private LocalDateTime createDate;

    private String original_name;
    private String mask_name;
    private String file_path;
    private String file_type;

    public ReportDTO saveFile(FileInfo fileInfo) {
        original_name = fileInfo.getOriginal_name();
        mask_name = fileInfo.getMask_name();
        file_path = fileInfo.getFile_path();
        file_type = fileInfo.getFile_type();
        return this;
    }
}
