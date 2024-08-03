package mungMo.member.response.publiccode.repository;

import mungMo.member.response.publiccode.entity.PublicCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicCodeRepository extends JpaRepository<PublicCodeEntity, Long> {
    List<PublicCodeEntity> findByCodeTypeAndUseYN(String type, boolean useYN);
}
