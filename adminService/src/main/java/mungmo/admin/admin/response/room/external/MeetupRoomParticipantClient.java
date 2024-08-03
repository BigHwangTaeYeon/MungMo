package mungmo.admin.admin.response.room.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="chat-service", url = "http://localhost:8000")
public interface MeetupRoomParticipantClient {
    @GetMapping(value = "/chat-service/v1/chatNonParticipants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> chatNonParticipants(@PathVariable(name = "chatRoomId") Long chatRoomId);
}
