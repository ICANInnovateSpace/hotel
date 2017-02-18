package com.ican.hotel.service;

import com.ican.hotel.beans.User;

import java.util.List;

/**
 * Created by mrzhou on 17-2-9.
 * 用户操作管理接口
 */
public interface IUserManager {
    /**
     * 添加用户信息
     *
     * @param user 用户数据
     * @return 成功或失败
     * */
    boolean add(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户数据
     * */
    void update(User user);

    /**
     * 根据用户id删除用户信息
     *
     * @param uid 用户id
     * @return 成功或失败
     * */
    boolean delete(String uid);

    /**
     * 通过用户id查询用户
     *
     * @param uid 用户id
     * @return 一个用户
     * */
    User queryByUid(String uid);

    /**
     * 通过用户名查询用户
     *
     * @param uname 用户名
     * @return 一个用户
     * */
    User queryByUname(String uname);

    /**
     * 通过用户名、密码查询用户
     *
     * @param uname 用户名
     * @param upsw 密码
     * @return 一个用户
     * */
    User query(String uname,String upsw);

    /**
     * 查询所有用户信息
     *
     * @return 用户列表
     * */
    List<User> getUsers();
}
