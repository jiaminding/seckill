package com.djm.seckill.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.djm.seckill.model.ItemDO;
import com.djm.seckill.dao.ItemDAO;
import com.djm.seckill.service.ItemService;

import java.util.List;

/**
 * @Description
 *
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:08
 */ 
@Service
public class ItemServiceImpl implements ItemService{

    @Resource
    private ItemDAO itemDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return itemDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ItemDO record) {
        return itemDAO.insert(record);
    }

    @Override
    public int insertSelective(ItemDO record) {
        return itemDAO.insertSelective(record);
    }

    @Override
    public ItemDO selectByPrimaryKey(Long id) {
        return itemDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemDO record) {
        return itemDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ItemDO record) {
        return itemDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<ItemDO> listAll() {
        return itemDAO.listAll();
    }

    @Override
    public int reduceStock(Long id, Integer quantity) {
        return itemDAO.reduceStock(id, quantity);
    }
}
