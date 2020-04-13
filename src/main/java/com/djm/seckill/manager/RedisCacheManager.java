package com.djm.seckill.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:22
 */
@Component
@Slf4j
public class RedisCacheManager<T> {

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void get(String key) {
        redisTemplate.opsForValue().get(key);
    }

    public Long decrease(String key, Integer descCount) {
        return redisTemplate.opsForValue().decrement(key, descCount);
    }
}
