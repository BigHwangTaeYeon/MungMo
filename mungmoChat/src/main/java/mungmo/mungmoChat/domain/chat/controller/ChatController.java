package mungmo.mungmoChat.domain.chat.controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ChatController {

    /**
     * 신고 종류
     * @return
     */
    @GetMapping("reportType")
    public ResponseEntity<?> reportType() {
        return ResponseEntity.ok(ResponseEntity.ok());
    }

    @Getter
    public static class data<T> {
        private final T data;
        public data(T data) {
            this.data = data;
        }
    }
}
