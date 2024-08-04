package mungMo.member.response.publiccode.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.town.infra.repository.SpringDataJpaTownRepository;
import mungMo.member.response.publiccode.entity.PublicCodeEntity;
import mungMo.member.response.publiccode.infra.PublicCodeRepository;
import mungMo.member.response.publiccode.infra.repository.SpringDataJpaPublicCodeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PublicCodeAdapter implements PublicCodeRepository {
    private final SpringDataJpaPublicCodeRepository townRepository;

    @Override
    public List<PublicCodeEntity> findByCodeTypeAndUseYN(String type, boolean useYN) {
        return townRepository.findByCodeTypeAndUseYN(type, useYN);
    }
}
