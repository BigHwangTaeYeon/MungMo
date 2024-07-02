package mungMo.boardService.domain.otherService.publicCode.service;

import mungMo.boardService.domain.otherService.publicCode.dto.PublicCodeDTO;
import mungMo.boardService.domain.otherService.publicCode.entity.PublicCodeEntity;
import mungMo.boardService.domain.otherService.publicCode.repository.PublicCodeRepository;
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
        return publicCodeRepository.findByCodeTypeAndUseYN("BDSJ", true)
                .stream()
                .map(PublicCodeEntity::changeToDTO)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
