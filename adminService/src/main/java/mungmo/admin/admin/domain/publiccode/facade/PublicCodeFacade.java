package mungmo.admin.admin.domain.publiccode.facade;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.domain.publiccode.domain.PublicCodeEntity;
import mungmo.admin.admin.domain.publiccode.external.PublicCodeDto;
import mungmo.admin.admin.domain.publiccode.service.PublicCodeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicCodeFacade {
    private final PublicCodeService publicCodeService;
    public List<PublicCodeDto> reportType() {
        return publicCodeService.reportType()
                .stream()
                .map(PublicCodeEntity::toDto)
                .collect(Collectors.toList());
    }
}
