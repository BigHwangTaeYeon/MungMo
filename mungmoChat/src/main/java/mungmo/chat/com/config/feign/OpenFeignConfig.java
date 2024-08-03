package mungmo.chat.com.config.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("mungmo.chat.response")
class OpenFeignConfig {

}
