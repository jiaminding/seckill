package com.djm.seckill.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.djm.seckill.model.RespResult;
import com.djm.seckill.service.SeckillService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author: 丁佳民
 * @Date: 2020/4/11 16:43
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Resource
    private SeckillService seckillService;

    @RequestMapping("do")
    @SentinelResource("seckill")
    public RespResult<Boolean> doSeckill(@RequestParam("itemId") Long itemId, @RequestParam("quantity") Integer quantity) {
        boolean isSuccess = seckillService.doSeckill(itemId, quantity);
        return RespResult.ofSuccess(isSuccess);
    }
}
