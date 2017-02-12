package com.ican.hotel.service;

import com.ican.hotel.beans.User;
import com.ican.hotel.dao.IUserDao;

import java.util.List;

/**
 * Created by mrzhou on 17-2-9.
 * 用户service层
 * 管理用户
 */
public class UserManager implements IUserManager {
    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 添加用户到数据库
     *
     * @param user 用户数据
     * */
    @Override
    public boolean add(User user) {
        //判断是否存在用户名相同的用户
        if (userDao.query(user.getUname()) != null)
            return false;
        //添加用户
        userDao.add(user);
        return true;
    }

    /**
     * 更新用户数据到数据库
     *
     * @param user 用户数据
     * */
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * 从数据库删除一条用户记录
     *
     * @param uid 用户id
     * */
    @Override
    public boolean delete(int uid) {
        User user = userDao.query(uid);
        if ( user== null)
            return false;
        userDao.delete(user);
        return true;
    }

    /**
     * 通过用户id查询用户
     *
     * @param uid 用户id
     * @return 一个用户
     * */
    @Override
    public User query(int uid) {
        return userDao.query(uid);
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
        return userDao.query(uname, upsw);
    }

    /**
     * 通过用户名查询用户
     *
     * @param uname 用户名
     * @return 一个用户
     * */
    @Override
    public User query(String uname) {
        return userDao.query(uname);
    }

    /**
     * 返回用户表里面的所有用户信息
     *
     * @return 用户列表
     * */
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
