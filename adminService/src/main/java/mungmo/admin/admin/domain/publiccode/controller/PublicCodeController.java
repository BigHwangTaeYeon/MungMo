package mungmo.admin.admin.domain.publiccode.controller;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.domain.publiccode.external.PublicCodeDto;
import mungmo.admin.admin.domain.publiccode.facade.PublicCodeFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/report")
@RequiredArgsConstructor
public class PublicCodeController {

    private final PublicCodeFacade publicCodeFacade;

    /**
     * 신고 종류
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PublicCodeDto>> reportType() {
        return ResponseEntity.ok(publicCodeFacade.reportType());
    }
}
