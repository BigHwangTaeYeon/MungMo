package mungMo.member.domain.town.controller;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.config.ResponseMessage;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.com.util.arg.UserDto;
import mungMo.member.domain.town.facade.TownFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/town")
@RequiredArgsConstructor
public class TownController {
    private final TownFacade townFacade;

    @PatchMapping
    public ResponseEntity<String> registerTown(UserDto userDto, @RequestParam String area) throws PreconditionFailedException {
        townFacade.register(area, userDto.getId());
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }
}
