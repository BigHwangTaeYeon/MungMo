package mungmo.board.domain.board.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mungmo.board.domain.board.entity.Subject;

@Getter @Setter
@AllArgsConstructor
public class PageDto {
    private Subject subject;
    private int page;
    private int size;
}
