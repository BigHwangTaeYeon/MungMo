package mungmo.chat.response.notification.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="admin-service", url = "http://localhost:8000")
public interface ChatNotificationClient {
    @PatchMapping("/admin-service/v1/changeStatusReadTrue")
    void changeStatusReadTrue(@RequestParam("roomId") Long roomId, @RequestParam("userId") Long userId);
}
