package com.djm.seckill;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SeckillApplication.class})
class SeckillApplicationTests {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testKafka() {
        kafkaTemplate.send("test", "test");
    }

    @Test
    void testRedis() {
//        redisTemplate.opsForValue().set("test3", 0);
        System.out.println(redisTemplate.opsForValue().decrement("test3", 1));
    }

    @Test
    @KafkaListener(topics = {"test"})
    void testKafkaConsumer(ConsumerRecord<String, String> record) {
        System.out.println(Thread.currentThread().getName());
    }

}
