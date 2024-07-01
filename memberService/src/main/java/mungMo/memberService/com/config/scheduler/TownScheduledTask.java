package mungMo.memberService.com.config.scheduler;

import lombok.extern.slf4j.Slf4j;
import mungMo.memberService.com.util.GetDate;
import mungMo.memberService.domain.town.entity.TownEntity;
import mungMo.memberService.domain.town.repository.TownRepository;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT57S")
@Component
public class TownScheduledTask {
    private final TownRepository townRepository;

    public TownScheduledTask(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(
            name = "town_scheduler_lock",
            lockAtLeastFor = "PT55S",
            lockAtMostFor = "PT55S"
    )
    @Transactional
    public void schedulerOneMin() {
        LocalDateTime date = GetDate.pareLocalDataTime("yyyyMMddHHmm");
        townRepository.bulkCertificationFalse(date.minusDays(30), date.minusDays(30).plusMinutes(1));
    }

    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void schedulerOneDay() {
        // 서버 다운 시켰을 때를 대비함
        LocalDateTime date = GetDate.pareLocalDataTime("yyyyMMddHHmm");
        townRepository.bulkCertificationFalseToDay(date.minusDays(31));
    }
}