package com.ican.hotel.dao;

import com.ican.hotel.beans.Order;

import java.util.List;

/**
 * Created by mrzhou on 17-2-18.
 * 订单数据库操作接口
 */
public interface IOrderDao {

    /**
     * 插入一条订单记录到数据库
     *
     * @param order 订单对象
     * */
    void add(Order order);

    /**
     * 从数据库删除一条订单记录
     *
     * @param order 订单对象
     * */
    void delete(Order order);

    /**
     * 更新一条订单记录到数据库
     *
     * @param order 订单对象
     **/
    void update(Order order);

    /**
     * 根据订单id查询某条订单记录
     *
     * @param oid 订单id
     * @return 一条订单记录
     * */
    Order queryByOid(String oid);

    /**
     * 根据客房号查询订单记录
     *
     * @param orid 客房号
     * @return 订单记录集合
     * */
    List<Order> queryByOrid(String orid);

    /**
     * 根据用户id查询订单记录
     *
     * @param ouid 用户id
     * @return 订单记录集合
     * */
    List<Order> queryByOuid(int ouid);

    /**
     * 获取所有订单记录
     *
     * @return 订单记录集合
     * */
    List<Order> getOrders();
}
