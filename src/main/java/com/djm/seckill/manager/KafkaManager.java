package com.djm.seckill.manager;

import com.djm.seckill.vo.SeckillVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @Description
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:14
 */
@Component
@Slf4j
public class KafkaManager<T> {

    @Resource
    private KafkaTemplate<String, T> kafkaTemplate;

    public boolean sendMessage(String topic, String key, T message) {
        ListenableFuture<SendResult<String, T>> listenableFuture = kafkaTemplate.send(topic, key, message);
        try {
            listenableFuture.get();
        } catch (InterruptedException e) {
            log.error("发送消息[topic:{}:value:{}]失败,原因", topic, message, e);
            return false;
        } catch (ExecutionException e) {
            log.error("发送消息[topic:{}:value:{}]失败,原因", topic, message, e);
            return false;
        } finally {
            kafkaTemplate.flush();
        }

        log.info("kafka send ok, topic = [{}],key= [{}],value = {}", topic, key, message);
        return true;
    }

}
