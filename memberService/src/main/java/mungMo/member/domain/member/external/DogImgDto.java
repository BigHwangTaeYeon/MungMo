package mungMo.member.domain.member.external;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class DogImgDto {
    private String originalName;
    private String maskName;
    private String filePath;
    private String fileType;
}
