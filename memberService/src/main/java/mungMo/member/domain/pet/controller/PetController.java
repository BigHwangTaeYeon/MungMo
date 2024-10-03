package mungMo.member.domain.pet.controller;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.config.ResponseMessage;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.com.util.arg.UserDto;
import mungMo.member.domain.pet.facade.PetFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetFacade petFacade;

    @PatchMapping
    public ResponseEntity<String> registerTown(UserDto userDto, @RequestParam String area) throws PreconditionFailedException {
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }
}
