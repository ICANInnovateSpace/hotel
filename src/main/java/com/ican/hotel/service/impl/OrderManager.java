package com.ican.hotel.service.impl;

import com.ican.hotel.beans.Order;
import com.ican.hotel.dao.IOrderDao;
import com.ican.hotel.service.IOrderManager;

import java.util.List;

/**
 * Created by mrzhou on 17-2-18.
 * 订单管理实现类
 */
public class OrderManager implements IOrderManager {
    private IOrderDao orderDao;

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * 添加订单信息
     *
     * @param order 订单信息对象
     * @return 成功或失败
     * */
    @Override
    public boolean add(Order order) {
        if (orderDao.queryByOid(order.getOid()) != null)
            return false;
        orderDao.add(order);
        return true;
    }

    /**
     * 根据订单id删除订单信息
     *
     * @param oid 订单id
     * @return 成功或失败
     * */
    @Override
    public boolean deleteByOid(String oid) {
        Order order = orderDao.queryByOid(oid);
        if (order == null)
            return false;
        orderDao.delete(order);
        return true;
    }

    /**
     * 根据客房号删除订单信息
     *
     * @param orid 客房号
     * @return 成功或失败
     * */
    @Override
    public boolean deleteByOrid(String orid) {
        List<Order> orders = orderDao.queryByOrid(orid);
        if (orders == null || orders.size() == 0)
            return false;
        for (Order order :
                orders) {
            orderDao.delete(order);
        }
        return true;
    }

    /**
     * 根据用户id删除订单信息
     *
     * @param ouid 用户id
     * @return 成功或失败
     * */
    @Override
    public boolean deleteByOuid(int ouid) {
        List<Order> orders = orderDao.queryByOuid(ouid);
        if (orders == null || orders.size() == 0)
            return false;
        for (Order order :
                orders) {
            orderDao.delete(order);
        }
        return true;
    }

    /**
     * 更新订单信息
     *
     * @param order 订单信息对象
     * @return 成功或失败
     * */
    @Override
    public boolean update(Order order) {
        if (orderDao.queryByOid(order.getOid()) == null)
            return false;
        orderDao.update(order);
        return true;
    }

    /**
     * 根据订单id查询订单信息
     *
     * @param oid 订单id
     * @return 订单信息对象
     * */
    @Override
    public Order queryByOid(String oid) {
        return orderDao.queryByOid(oid);
    }

    /**
     * 根据客房号查询订单信息
     *
     * @param orid 客房号
     * @return 订单信息集合
     * */
    @Override
    public List<Order> queryByOrid(String orid) {
        return orderDao.queryByOrid(orid);
    }

    /**
     * 根据用户id查询订单信息
     *
     * @param ouid 用户id
     * @return 订单信息集合
     * */
    @Override
    public List<Order> queryByOuid(int ouid) {
        return orderDao.queryByOuid(ouid);
    }

    /**
     * 查询所有订单信息
     *
     * @return 订单信息集合
     * */
    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }
}
