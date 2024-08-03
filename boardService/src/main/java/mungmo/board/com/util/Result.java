package mungmo.board.com.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Result<T> {
    private final T data;
}
