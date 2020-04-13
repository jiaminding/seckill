package com.djm.seckill.service;

import com.djm.seckill.model.OrderDO;
    /**
 * @Description
 *
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:08
 */ 
public interface OrderService{


    int deleteByPrimaryKey(Long id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);

}
