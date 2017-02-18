package com.ican.hotel.service;

import com.ican.hotel.beans.Room;

import java.util.List;

/**
 * Created by mrzhou on 17-2-13.
 * 客房操作管理接口
 */
public interface IRoomManager {

    /**
     * 添加客房信息
     *
     * @param room 客房信息对象
     * @return 成功或失败
     * */
    boolean add(Room room);

    /**
     * 删除客房信息
     *
     * @param room 客房信息对象
     * @return 成功或失败
     * */
    boolean delete(Room room);

    /**
     * 更新客房信息
     *
     * @param room 客房信息对象
     * @return 成功或失败
     */
    boolean update(Room room);

    /**
     * 根据id查询客房信息
     *
     * @param rid 客房id
     * @return 包含一条客房记录的对象
     */
    Room queryByRid(String rid);

    /**
     * 根据客房类型查询客房信息
     *
     * @param rtype 客房类型
     * @return 客房信息列表
     */
    List<Room> queryByRtype(String rtype);

    /**
     * 查询全部客房信息
     *
     * @return 客房信息列表
     */
    List<Room> getRooms();
}
