package mungmo.admin.admin.domain.publiccode.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.admin.admin.domain.publiccode.domain.PublicCodeEntity;
import mungmo.admin.admin.domain.publiccode.infra.PublicCodeRepository;
import mungmo.admin.admin.domain.publiccode.infra.repository.SpringDataJpaPublicCodeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PublicCodeAdapter implements PublicCodeRepository {
    private final SpringDataJpaPublicCodeRepository publicCodeRepository;

    @Override
    public List<PublicCodeEntity> findByCodeTypeAndUseYN(String type, boolean useYN) {
        return publicCodeRepository.findByCodeTypeAndUseYN(type, useYN);
    }
}
