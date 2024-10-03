package mungMo.member.domain.report.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReportDto {
    private Long seq;

    private String title;
    private String content;

    private Long fromId;
    private Long toId;

    private useStatus status;

    private LocalDateTime createDate;

    private String originalName;
    private String maskName;
    private String filePath;
    private String fileType;

    public enum useStatus {
        DID, DIDNOT
    }

    public ReportDto setFromId(Long id){
        fromId = id;
        return this;
    }
}
