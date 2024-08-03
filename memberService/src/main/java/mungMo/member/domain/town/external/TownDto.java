package mungMo.member.domain.town.external;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TownDto {
    private Long town_id;
    private Long member_id;
    private String area;
    private boolean certification;
    private LocalDateTime certification_date;
}
