package mungMo.member.response.publiccode.infra;

import mungMo.member.response.publiccode.entity.PublicCodeEntity;

import java.util.List;

public interface PublicCodeRepository {
    List<PublicCodeEntity> findByCodeTypeAndUseYN(String type, boolean useYN);
}
