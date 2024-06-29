package mungMo.memberService.domain.otherService.publicCode.service;

import mungMo.memberService.domain.otherService.publicCode.dto.PublicCodeDTO;
import mungMo.memberService.domain.otherService.publicCode.entity.PublicCodeEntity;
import mungMo.memberService.domain.otherService.publicCode.repository.PublicCodeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicCodeService {
    private final PublicCodeRepository publicCodeRepository;

    public PublicCodeService(PublicCodeRepository publicCodeRepository) {
        this.publicCodeRepository = publicCodeRepository;
    }

    public List<PublicCodeDTO> reportType() {
        return publicCodeRepository.findByCodeTypeAndUseYN("RPRT", true)
                .stream()
                .map(PublicCodeEntity::changeToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
