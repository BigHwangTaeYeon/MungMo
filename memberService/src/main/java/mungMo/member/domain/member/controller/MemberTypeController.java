package mungMo.member.domain.member.controller;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.config.ResponseMessage;
import mungMo.member.com.util.Result;
import mungMo.member.com.util.arg.UserDto;
import mungMo.member.domain.member.external.MemberTypeDto;
import mungMo.member.domain.member.facade.MemberTypeFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/member/type")
@RequiredArgsConstructor
public class MemberTypeController {
    private final MemberTypeFacade memberTypeService;

    /**
     * 유저 모든 타입 가져오기
     * @return
     */
    @GetMapping
    public ResponseEntity<Result<List<MemberTypeDto>>> userTypeList(UserDto userDto) {
        return ResponseEntity.ok(new Result<>(
                memberTypeService.userTypeList(userDto.getId())
        ));
    }

    /**
     * 유저 타입 저장
     * @param code
     * @return
     */
    @PatchMapping
    public ResponseEntity<String> userTypeResister(UserDto userDto, @RequestParam List<Integer> code) {
        memberTypeService.userTypeResister(userDto.getId(), code);
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }

    /**
     * 강아지 타입 저장
     * @param code
     * @return
     */
    @PatchMapping("/dogTypeResister")
    public ResponseEntity<String> dogTypeResister(UserDto userDto, @RequestParam List<Integer> code) {
        memberTypeService.dogTypeResister(userDto.getId(), code);
        return ResponseEntity.ok(ResponseMessage.OK.getMessage());
    }

}