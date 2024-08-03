package mungmo.board.domain.board.external;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CommentDto {
    private Long boardId;
    private String comment;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
