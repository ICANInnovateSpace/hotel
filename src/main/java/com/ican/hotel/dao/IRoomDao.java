package com.ican.hotel.dao;

import com.ican.hotel.beans.Room;

import java.util.List;

/**
 * Created by mrzhou on 17-2-13.
 * 客房数据库操作接口
 */
public interface IRoomDao {

    /**
     * 插入房间信息到数据库
     *
     * @param room 客房信息对象
     * */
    void add(Room room);

    /**
     * 从数据库删除一条房间记录（根据rid删除）
     *
     * @param room 客房信息对象
     * */
    void delete(Room room);

    /**
     * 更新一条客房记录到数据
     *
     * @param room 客房信息对象
     * */
    void update(Room room);

    /**
     * 根据room id查询一条客房数据库记录
     *
     * @param rid 客房id
     * @return 包含一条客房记录的对象
     * */
    Room queryByRid(String rid);

    /**
     * 根据客房类型查询一条客房记录
     *
     * @param rtype 客房类型
     * @return 客房信息列表
     * */
    List<Room> queryByRtype(String rtype);

    /**
     * 返回全部客房信息
     * @return 客房信息列表
     * */
    List<Room> getRooms();
}
