package mungmo.chat.domain.embede;

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
    private String original_name;
    private String mask_name;
    private String file_path;
    private String file_type;

    public FileInfo(Map<String, String> uploadFileInfo, String file_type) {
        this.original_name = uploadFileInfo.get("original");
        this.mask_name = uploadFileInfo.get("masking");
        this.file_path = uploadFileInfo.get("path");
        this.file_type = file_type;
    }
}
