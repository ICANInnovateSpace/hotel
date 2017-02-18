package com.ican.hotel.dao.impl;

import com.ican.hotel.beans.Order;
import com.ican.hotel.dao.IOrderDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by mrzhou on 17-2-18.
 * 订单数据库操作实现类
 */
public class OrderDao implements IOrderDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 插入一条订单记录到数据库
     *
     * @param order 订单对象
     */
    @Override
    public void add(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    /**
     * 从数据库删除一条订单记录
     *
     * @param order 订单对象
     */
    @Override
    public void delete(Order order) {
        sessionFactory.getCurrentSession().delete(order);
    }

    /**
     * 更新一条订单记录到数据库
     *
     * @param order 订单对象
     **/
    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

    /**
     * 根据订单id查询某条订单记录
     *
     * @param oid 订单id
     * @return 一条订单记录
     */
    @Override
    public Order queryByOid(String oid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Order where oid=?0");
        query.setParameter("0", oid);
        List list = query.list();
        if (list.size() > 0) {
            return (Order) list.get(0);
        }
        return null;
    }

    /**
     * 根据客房号查询订单记录
     *
     * @param orid 客房号
     * @return 订单记录集合
     */
    @Override
    public List<Order> queryByOrid(String orid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Order where orid=?0");
        query.setParameter("0", orid);
        List list = query.list();
        return list;
    }

    /**
     * 根据用户id查询订单记录
     *
     * @param ouid 用户id
     * @return 订单记录集合
     */
    @Override
    public List<Order> queryByOuid(int ouid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Order where ouid=?0");
        query.setParameter("0", ouid);
        List list = query.list();
        return list;
    }

    /**
     * 获取所有订单记录
     *
     * @return 订单记录集合
     */
    @Override
    public List<Order> getOrders() {
        return sessionFactory.getCurrentSession().createQuery("from Order").list();
    }
}
