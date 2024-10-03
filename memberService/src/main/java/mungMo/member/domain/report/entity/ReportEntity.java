package mungMo.member.domain.report.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import mungMo.member.domain.embede.FileInfo;
import mungMo.member.domain.report.external.ReportDto;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "Report")
@NoArgsConstructor
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

    public ReportEntity(FileInfo fileInfo, ReportDto dto) {
        if(Optional.ofNullable(fileInfo).isPresent()) this.fileInfo = fileInfo;
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.fromMemberId = dto.getFromId();
        this.toMemberId = dto.getToId();
    }

    public ReportEntity(ReportDto dto) {
        title = dto.getTitle();
        content = dto.getContent();
        fromMemberId = dto.getFromId();
        toMemberId = dto.getToId();
        status = false;
        create_date = LocalDateTime.now();
        if(StringUtils.hasText(dto.getFilePath())) fileInfo = new FileInfo(dto.getOriginalName(), dto.getMaskName(), dto.getFilePath(), dto.getFileType());
    }

    public ReportDto toDto() {
        if(Optional.ofNullable(fileInfo).isPresent()) {
            return ReportDto.builder()
                    .seq(id)
                    .title(title)
                    .content(content)
                    .fromId(fromMemberId)
                    .toId(toMemberId)
                    .status(status ? ReportDto.useStatus.DID : ReportDto.useStatus.DIDNOT)
                    .createDate(create_date)
                    .filePath(fileInfo.getFilePath())
                    .originalName(fileInfo.getOriginalName())
                    .maskName(fileInfo.getMaskName())
                    .fileType(fileInfo.getFileType())
                    .build();
        } else {
            return ReportDto.builder()
                    .seq(id)
                    .title(title)
                    .content(content)
                    .fromId(fromMemberId)
                    .toId(toMemberId)
                    .status(status ? ReportDto.useStatus.DID : ReportDto.useStatus.DIDNOT)
                    .createDate(create_date)
                    .build();
        }
    }
}
