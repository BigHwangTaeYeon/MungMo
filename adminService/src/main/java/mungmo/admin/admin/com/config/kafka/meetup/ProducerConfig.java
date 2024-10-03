package mungmo.admin.admin.com.config.kafka.meetup;

import com.google.common.collect.ImmutableMap;
import mungmo.admin.admin.domain.notification.vo.ChatNotificationVo;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@EnableKafka
@Configuration
public class ProducerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;

    // 채팅 메시지 알림을 위한 kafka 설정
    @Bean
    public ProducerFactory<String, ChatNotificationVo> notificationProducerFactory() {
        return new DefaultKafkaProducerFactory<>(notificationProducerConfigurations());
    }

    @Bean
    public Map<String, Object> notificationProducerConfigurations() {
        return ImmutableMap.<String, Object>builder()
                .put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer)
                .put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
                .put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
                .build();
    }

    @Bean
    public KafkaTemplate<String, ChatNotificationVo> notificationKafkaTemplate() {
        return new KafkaTemplate<>(notificationProducerFactory());
    }
}