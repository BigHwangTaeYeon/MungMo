package mungMo.memberService.domain.report.entity;

import jakarta.persistence.*;
import mungMo.memberService.com.util.GetDate;
import mungMo.memberService.domain.embede.FileInfo;
import mungMo.memberService.domain.report.dto.ReportDTO;

import java.time.LocalDateTime;

@Entity
@Table(name = "Report")
public class ReportEntity {
    // memberId 참조
    @Id
    @Column(name = "report_id")
    private Long id;

    private String title;
    private String content;

    @Column(name = "from_member_id", nullable = false)
    private Long fromMemberId;

    @Column(name = "to_member_id", nullable = false)
    private Long toMemberId;

    private boolean status;

    private LocalDateTime create_date;

    @Embedded
    private FileInfo fileInfo;

    public ReportEntity() {
    }

    public ReportEntity(ReportDTO dto) {
        title = dto.getTitle();
        content = dto.getContent();
        fromMemberId = dto.getFromId();
        toMemberId = dto.getToId();
        status = false;
        create_date = LocalDateTime.parse(GetDate.getCurrentTime("YYYYMMDDHHmmss"));
        if(!dto.getFile_path().isEmpty()) {
            fileInfo = new FileInfo(dto.getOriginal_name(), dto.getMask_name(), dto.getFile_path(), dto.getFile_type());
        }
    }

    public ReportDTO changeToDTO() {
        return ReportDTO.builder()
                .title(title)
                .content(content)
                .fromId(fromMemberId)
                .toId(toMemberId)
                .status(status)
                .createDate(create_date)
                .build();
    }
}
