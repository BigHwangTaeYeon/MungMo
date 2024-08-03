package mungmo.board.domain.board.external;

import lombok.Builder;

@Builder
public class DogBoardDto {
    private DogInfoDto dogInfo;
    private BoardDto board;
}
