package com.djm.seckill.dao;

import com.djm.seckill.model.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 *
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:08
 */
@Mapper
public interface OrderDAO {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}