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
    @Id @GeneratedValue
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

    public ReportEntity(FileInfo fileInfo, ReportDTO dto) {
        if(!dto.getFile_path().isEmpty()) {
            this.fileInfo = fileInfo;
        }
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.fromMemberId = dto.getFromId();
        this.toMemberId = dto.getToId();
    }

    public ReportEntity(ReportDTO dto) {
        title = dto.getTitle();
        content = dto.getContent();
        fromMemberId = dto.getFromId();
        toMemberId = dto.getToId();
        status = false;
        create_date = GetDate.pareLocalDataTime("yyyyMMddHHmmss");
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
                .status(status == true ? ReportDTO.useStatus.DID : ReportDTO.useStatus.DIDNOT)
                .createDate(create_date)
                .file_path(fileInfo.getFile_path())
                .original_name(fileInfo.getOriginal_name())
                .mask_name(fileInfo.getMask_name())
                .file_type(fileInfo.getFile_type())
                .build();
    }
}
