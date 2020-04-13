package com.djm.seckill.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:53
 */
@Data
public class SeckillVO implements Serializable {

    private static final long serialVersionUID = 2580235976443257151L;

    private Long itemId;

    private Integer quantity;

    private Long userId;
}
