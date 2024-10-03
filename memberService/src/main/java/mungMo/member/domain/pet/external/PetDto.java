package mungMo.member.domain.pet.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDto {
    private Long id;
    private String name;
    private String type;
    private String birthday;
    private String gender;
    private boolean use;

    private String originalName;
    private String maskName;
    private String filePath;
    private String fileType;
}
