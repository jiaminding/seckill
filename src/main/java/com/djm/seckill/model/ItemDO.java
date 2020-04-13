package com.djm.seckill.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Description
 *
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:08
 */ 
@Data
public class ItemDO implements Serializable {
    private Long id;

    private String name;

    private Long price;

    private Integer stockCount;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}