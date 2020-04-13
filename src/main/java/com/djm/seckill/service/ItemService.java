package com.djm.seckill.service;

import com.djm.seckill.model.ItemDO;

import java.util.List;

/**
 * @Description
 *
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:08
 */ 
public interface ItemService{


    int deleteByPrimaryKey(Long id);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);

    List<ItemDO> listAll();

    int reduceStock(Long id, Integer quantity);

}
