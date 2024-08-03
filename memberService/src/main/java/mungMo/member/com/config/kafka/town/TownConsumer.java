package mungMo.member.com.config.kafka.town;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.town.external.TownDto;
import mungMo.member.domain.town.facade.TownFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TownConsumer {
    private final TownFacade townFacade;

    @KafkaListener(topics = "${kafka.town.filter.topic}", groupId = "${kafka.town.consumer.id}", containerFactory = "kafkaTownContainerFactory")
    public void consumeMessage(TownDto town) {
        // 인증 취소
        townFacade.cancelCertification(town.getTown_id());
    }
}
