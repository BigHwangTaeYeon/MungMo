package mungmo.admin.admin.domain.publiccode.infra;

import mungmo.admin.admin.domain.publiccode.domain.PublicCodeEntity;

import java.util.List;

public interface PublicCodeRepository {
    List<PublicCodeEntity> findByCodeTypeAndUseYN(String type, boolean useYN);
}
