package mungMo.member.domain.report.controller;


import lombok.RequiredArgsConstructor;
import mungMo.member.com.exception.FileUploadException;
import mungMo.member.com.util.Result;
import mungMo.member.com.config.ResponseMessage;
import mungMo.member.com.util.arg.UserDto;
import mungMo.member.domain.report.external.ReportDto;
import mungMo.member.domain.report.facade.ReportFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportFacade reportFacade;

    @GetMapping
    public ResponseEntity<Result<List<ReportDto>>> reportList(UserDto userDto) {
        return ResponseEntity.ok(new Result<>(
                reportFacade.userReportList(userDto.getId())
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<ReportDto>> reportDetail(@PathVariable("id") long id) {
        return ResponseEntity.ok(
                new Result<>(reportFacade.reportDetail(id))
        );
    }

    @PostMapping
    public ResponseEntity<String> resisterReport(ReportDto reportDTO, MultipartFile file, UserDto userDto) throws FileUploadException {
        reportFacade.register(reportDTO.setFromId(userDto.getId()), file);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }
}
