package mungMo.member.domain.member.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class DogImgDto {
    private String original_name;
    private String mask_name;
    private String file_path;
    private String file_type;
}
