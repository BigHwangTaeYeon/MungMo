package mungMo.memberService.domain.member.town.repository;

import mungMo.memberService.domain.member.town.entity.TownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<TownEntity, Long> {
}
