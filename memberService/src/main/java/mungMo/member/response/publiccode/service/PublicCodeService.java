package mungMo.member.response.publiccode.service;

import lombok.RequiredArgsConstructor;
import mungMo.member.response.publiccode.entity.PublicType;
import mungMo.member.response.publiccode.external.PublicCodeDto;
import mungMo.member.response.publiccode.entity.PublicCodeEntity;
import mungMo.member.response.publiccode.repository.PublicCodeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static mungMo.member.response.publiccode.entity.PublicType.RPRT;

@Service
@RequiredArgsConstructor
public class PublicCodeService {
    private final PublicCodeRepository publicCodeRepository;

    public List<PublicCodeEntity> reportType() {
        return publicCodeRepository.findByCodeTypeAndUseYN(PublicType.str(RPRT), true);
    }
}
