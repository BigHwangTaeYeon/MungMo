package mungmo.admin.admin.response.member.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="member-service", url = "http://localhost:8000")
public interface MemberClient {
    @GetMapping(value = "/member-service/v1/member/feignClient/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getMember(@PathVariable(name = "id") Long userId);
}
