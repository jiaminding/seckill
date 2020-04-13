package com.djm.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.djm.seckill.manager.KafkaManager;
import com.djm.seckill.manager.RedisCacheManager;
import com.djm.seckill.model.ItemDO;
import com.djm.seckill.model.OrderDO;
import com.djm.seckill.service.ItemService;
import com.djm.seckill.service.OrderService;
import com.djm.seckill.service.SeckillService;
import com.djm.seckill.vo.SeckillVO;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @Description
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:49
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private ItemService itemService;

    @Resource
    private OrderService orderService;

    @Resource
    private KafkaManager<String> kafkaManager;

    @Resource
    private RedisCacheManager<Integer> redisCacheManager;

    @Value("seckill")
    private String SECKILL_KAFKA_TOPIC;

    @Value("seckill:item:")
    private String SECKILL_REDIS_PREFIX;

    private Map<Long, Boolean> stockOverMap = Maps.newHashMap();

    private ExecutorService threadPool = new ThreadPoolExecutor(16, 16,60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    private Semaphore semaphore = new Semaphore(100);

    @Override
    public boolean doSeckill(Long itemId, Integer quantity) {
        Boolean isStockOver = stockOverMap.get(itemId);
        if (null == isStockOver) {
            return false;
        }
        if (isStockOver) {
            return false;
        }
        Long stock = redisCacheManager.decrease(SECKILL_REDIS_PREFIX + itemId, quantity);
        if (stock < 0) {
            stockOverMap.put(itemId, true);
            return false;
        }

        SeckillVO seckillVO = new SeckillVO();
        seckillVO.setItemId(itemId);
        seckillVO.setQuantity(quantity);
        seckillVO.setUserId(1L);
        kafkaManager.sendMessage(SECKILL_KAFKA_TOPIC, "request", JSON.toJSONString(seckillVO));
        return true;
    }

    @KafkaListener(topics = {"seckill"})
    public void receive(ConsumerRecord<String, String> record) {
        log.info("kafka消息" + record.toString());
        Optional<String> value = Optional.ofNullable(record.value());
        if (value.isPresent()) {
            try {
                semaphore.acquire();
                threadPool.execute(() -> {
                    try {
                        kill(JSON.parseObject(value.get(), SeckillVO.class));
                    } finally {
                        semaphore.release();
                    }
                });
            } catch (InterruptedException e) {
                log.error("获取信号量失败: {}", e.getMessage());
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public OrderDO kill(SeckillVO seckillVO) {
        itemService.reduceStock(seckillVO.getItemId(), seckillVO.getQuantity());
        OrderDO orderDO = new OrderDO();
        orderDO.setItemId(seckillVO.getItemId());
        orderDO.setQuantity(seckillVO.getQuantity());
        orderDO.setUserId(seckillVO.getUserId());
        orderService.insertSelective(orderDO);

        return orderDO;
    }

    @PostConstruct
    public void init() {
        List<ItemDO> itemDOs = itemService.listAll();
        if (CollectionUtils.isEmpty(itemDOs)) {
            return;
        }
        itemDOs.stream().forEach(item -> {
            redisCacheManager.set(SECKILL_REDIS_PREFIX + item.getId(), item.getStockCount());
            stockOverMap.put(item.getId(), false);
        });
    }
}
