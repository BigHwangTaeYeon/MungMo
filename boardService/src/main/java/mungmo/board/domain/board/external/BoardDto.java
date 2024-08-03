package mungmo.board.domain.board.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mungmo.board.domain.board.entity.Subject;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private Subject subject;

    private String original_name;
    private String mask_name;
    private String file_path;
    private String file_type;

    public BoardDto setFile(Map<String, String> uploadFileInfo) {
        if(StringUtils.hasText(uploadFileInfo.get("original"))) {
            this.original_name = uploadFileInfo.get("original");
            this.mask_name = uploadFileInfo.get("masking");
            this.file_path = uploadFileInfo.get("path");
        }
        return this;
    }
}
