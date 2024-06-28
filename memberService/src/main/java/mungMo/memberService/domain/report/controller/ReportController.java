package mungMo.memberService.domain.report.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import mungMo.memberService.com.annotation.LoginCheckEssential;
import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.domain.member.oauth.jwt.AuthTokensGenerator;
import mungMo.memberService.domain.member.oauth.jwt.JwtTokenProvider;
import mungMo.memberService.domain.report.dto.ReportDTO;
import mungMo.memberService.domain.report.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
public class ReportController {
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtProvider;
    private final ReportService reportService;

    public ReportController(AuthTokensGenerator authTokensGenerator, JwtTokenProvider jwtProvider, ReportService reportService) {
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
        this.reportService = reportService;
    }

    @LoginCheckEssential
    @GetMapping("/reportList")
    public ResponseEntity<?> reportList(HttpServletRequest request) {
        return ResponseEntity.ok(
                new Result<>(reportService.userReportList(
                        authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))
                ))
        );
    }

    @LoginCheckEssential
    @GetMapping("/reportDetail/{id}")
    public ResponseEntity<?> reportDetail(HttpServletRequest request, @PathVariable("id") long toId) {
        return ResponseEntity.ok(
                new Result<>(reportService.reportDetail(
                        authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)),
                        toId
                ))
        );
    }

    @LoginCheckEssential
    @PostMapping("/resisterReport")
    public ResponseEntity<?> resisterReport(ReportDTO reportDTO, MultipartFile file) {
        reportService.register(reportDTO, file);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @Getter
    public static class Result<T> {
        private final T data;

        public Result(T data) {
            this.data = data;
        }
    }

}
