package mungMo.member.domain.embede;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String originalName;
    private String maskName;
    private String filePath;
    private String fileType;

    public FileInfo(Map<String, String> uploadFileInfo, String file_type) {
        this.originalName = uploadFileInfo.get("original");
        this.maskName = uploadFileInfo.get("masking");
        this.filePath = uploadFileInfo.get("path");
        this.fileType = file_type;
    }
}
