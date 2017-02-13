package com.ican.hotel.dao.impl;

import com.ican.hotel.beans.User;
import com.ican.hotel.dao.IUserDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by mrzhou on 17-2-9.
 * <p>
 * 操作数据库User表的实现类
 * 实现用户的增、删、查、改
 */
public class UserDao implements IUserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 插入用户数据到User表
     *
     * @param user 用户数据
     */
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    /**
     * 从数据库User表中删除与user匹配的一条记录
     *
     * @param user 用户数据
     */
    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    /**
     * 更新数据库user数据到User表中
     *
     * @param user 用户数据
     */
    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    /**
     * 通过用户id查询用户
     *
     * @param uid 用户id
     * @return 一个用户
     * */
    @Override
    public User query(int uid) {
        return (User) sessionFactory.getCurrentSession().get(User.class,uid);
    }

    /**
     * 通过用户名、密码查询用户
     *
     * @param uname 用户名
     * @param upsw 密码
     * @return 一个用户
     * */
    @Override
    public User query(String uname, String upsw) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where uname=?0 and  upsw=?1");
        query.setParameter("0",uname);
        query.setParameter("1",upsw);
        List list = query.list();
        if (list.size() != 0)
            return (User) list.get(0);
        return null;
    }

    /**
     * 通过用户名查询用户
     *
     * @param uname 用户名
     * @return 一个用户
     * */
    @Override
    public User query(String uname){
        Query query = sessionFactory.getCurrentSession().createQuery("from User where uname=?0");
        query.setParameter("0",uname);
        List list = query.list();
        if (list.size() != 0)
            return (User) list.get(0);
        return null;
    }

    /**
     * 返回用户表里面的所有用户信息
     *
     * @return 用户列表
     * */
    @Override
    public List<User> getUsers() {
        Query query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.list();
    }
}
