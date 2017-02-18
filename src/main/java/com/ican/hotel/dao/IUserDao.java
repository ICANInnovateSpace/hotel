package com.ican.hotel.dao;

import com.ican.hotel.beans.User;

import java.util.List;

/**
 * Created by mrzhou on 17-2-9.
 * 用户数据库操作接口
 */
public interface IUserDao {
    /**
     * 添加用户到数据库
     *
     * @param user 用户数据
     * */
    void add(User user);

    /**
     * 从数据库删除一条用户记录
     *
     * @param user 用户数据
     * */
    void delete(User user);

    /**
     * 更新用户数据到数据库
     *
     * @param user 用户数据
     * */
    void update(User user);

    /**
     * 通过用户id查询用户
     *
     * @param uid 用户id
     * @return 一个用户
     * */
    User queryByUid(String uid);

    /**
     * 通过用户名、密码查询用户
     *
     * @param uname 用户名
     * @param upsw 密码
     * @return 一个用户
     * */
    User query(String uname,String upsw);

    /**
     * 通过用户名查询用户
     *
     * @param uname 用户名
     * @return 一个用户
     * */
    User queryByUname(String uname);

    /**
     * 返回用户表里面的所有用户信息
     *
     * @return 用户列表
     * */
    List<User> getUsers();
}
