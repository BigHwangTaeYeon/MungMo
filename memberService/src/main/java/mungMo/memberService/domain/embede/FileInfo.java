package mungMo.memberService.domain.embede;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import mungMo.memberService.com.util.Upload;

import java.util.Map;

@Embeddable
@Getter
public class FileInfo {
    private final String original_name;
    private final String mask_name;
    private final String file_path;
    private final String file_type;

    public FileInfo(Map<String, String> uploadFileInfo, String file_type) {
        this.original_name = uploadFileInfo.get("original");
        this.mask_name = uploadFileInfo.get("masking");
        this.file_path = uploadFileInfo.get("path");
        this.file_type = file_type;
    }
}
