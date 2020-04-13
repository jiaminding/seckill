package com.djm.seckill.service;

/**
 * @Description
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:39
 */
public interface SeckillService {

    boolean doSeckill(Long itemId, Integer quantity);
}
