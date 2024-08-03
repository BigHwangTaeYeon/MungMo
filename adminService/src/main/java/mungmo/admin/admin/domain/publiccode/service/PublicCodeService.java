package mungmo.admin.admin.domain.publiccode.service;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.domain.publiccode.domain.PublicCodeEntity;
import mungmo.admin.admin.domain.publiccode.domain.PublicType;
import mungmo.admin.admin.domain.publiccode.repository.PublicCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static mungmo.admin.admin.domain.publiccode.domain.PublicType.RPRT;

@Service
@RequiredArgsConstructor
public class PublicCodeService {
    private final PublicCodeRepository publicCodeRepository;

    public List<PublicCodeEntity> reportType() {
        return publicCodeRepository.findByCodeTypeAndUseYN(PublicType.str(RPRT), true);
    }
}
