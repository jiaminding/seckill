package com.djm.seckill.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.djm.seckill.dao.OrderDAO;
import com.djm.seckill.model.OrderDO;
import com.djm.seckill.service.OrderService;
/**
 * @Description
 *
 * @Author: 丁佳民
 * @Date: 2020/4/11 15:08
 */ 
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderDAO orderDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderDO record) {
        return orderDAO.insert(record);
    }

    @Override
    public int insertSelective(OrderDO record) {
        return orderDAO.insertSelective(record);
    }

    @Override
    public OrderDO selectByPrimaryKey(Long id) {
        return orderDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderDO record) {
        return orderDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderDO record) {
        return orderDAO.updateByPrimaryKey(record);
    }

}
