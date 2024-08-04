package mungMo.member.response.publiccode.service;

import lombok.RequiredArgsConstructor;
import mungMo.member.response.publiccode.entity.PublicType;
import mungMo.member.response.publiccode.entity.PublicCodeEntity;
import mungMo.member.response.publiccode.infra.PublicCodeRepository;
import mungMo.member.response.publiccode.infra.repository.SpringDataJpaPublicCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static mungMo.member.response.publiccode.entity.PublicType.RPRT;

@Service
@RequiredArgsConstructor
public class PublicCodeService {
    private final PublicCodeRepository publicCodeRepository;

    public List<PublicCodeEntity> findByCodeTypeAndUseYN(String type, boolean useYN) {
        return publicCodeRepository.findByCodeTypeAndUseYN(type, useYN);
    }
}
