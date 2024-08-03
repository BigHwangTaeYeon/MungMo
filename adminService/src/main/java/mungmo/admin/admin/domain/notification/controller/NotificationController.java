package mungmo.admin.admin.domain.notification.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mungmo.admin.admin.com.config.ResponseMessage;
import mungmo.admin.admin.domain.notification.facade.NotificationFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/Notification")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationFacade notificationFacade;

    @PatchMapping
    public ResponseEntity<String> changeStatusReadTrue(Long roomId, Long userId) {
        notificationFacade.changeStatusReadTrue(userId, roomId);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }
}
