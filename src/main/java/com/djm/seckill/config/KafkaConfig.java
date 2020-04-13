package com.djm.seckill.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author: 丁佳民
 * @Date: 2020/4/13 10:01
 */
@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("seckill", 8, (short) 1);
    }
}
