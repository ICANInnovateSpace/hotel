package com.ican.hotel.service;

import com.ican.hotel.beans.Order;

import java.util.List;

/**
 * Created by mrzhou on 17-2-18.
 * 订单管理接口
 */
public interface IOrderManager {

    /**
     * 添加订单信息
     *
     * @param order 订单信息对象
     * @return 成功或失败
     * */
    boolean add(Order order);

    /**
     * 根据订单id删除订单信息
     *
     * @param oid 订单id
     * @return 成功或失败
     * */
    boolean deleteByOid(String oid);

    /**
     * 根据客房号删除订单信息
     *
     * @param orid 客房号
     * @return 成功或失败
     * */
    boolean deleteByOrid(String orid);

    /**
     * 根据用户id删除订单信息
     *
     * @param ouid 用户id
     * @return 成功或失败
     * */
    boolean deleteByOuid(String ouid);

    /**
     * 更新订单信息
     *
     * @param order 订单信息对象
     * @return 成功或失败
     * */
    boolean update(Order order);

    /**
     * 根据订单id查询订单信息
     *
     * @param oid 订单id
     * @return 订单信息对象
     * */
    Order queryByOid(String oid);

    /**
     * 根据客房号查询订单信息
     *
     * @param orid 客房号
     * @return 订单信息集合
     * */
    List<Order> queryByOrid(String orid);

    /**
     * 根据用户id查询订单信息
     *
     * @param ouid 用户id
     * @return 订单信息集合
     * */
    List<Order> queryByOuid(String ouid);

    /**
     * 查询所有订单信息
     *
     * @return 订单信息集合
     * */
    List<Order> getOrders();
}
