package mungmo.board.domain.embede;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String original_name;
    private String mask_name;
    private String file_path;
    private String file_type;
}
