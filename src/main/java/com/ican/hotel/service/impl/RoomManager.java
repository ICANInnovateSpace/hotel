package com.ican.hotel.service.impl;

import com.ican.hotel.beans.Room;
import com.ican.hotel.dao.IRoomDao;
import com.ican.hotel.service.IRoomManager;

import java.util.List;

/**
 * Created by mrzhou on 17-2-13.
 * 客房操作管理实现类
 */
public class RoomManager implements IRoomManager{
    private IRoomDao roomDao;

    public void setRoomDao(IRoomDao roomDao) {
        this.roomDao = roomDao;
    }

    /**
     * 添加客房信息
     *
     * @param room 客房信息对象
     * @return 成功或失败
     * */
    @Override
    public boolean add(Room room) {
        if (roomDao.queryByRid(room.getRid()) == null)
            return false;
        roomDao.add(room);
        return true;
    }

    /**
     * 删除客房信息
     *
     * @param room 客房信息对象
     * @return 成功或失败
     * */
    @Override
    public boolean delete(Room room) {
        if (roomDao.queryByRid(room.getRid()) == null)
            return false;
        roomDao.delete(room);
        return true;
    }

    /**
     * 更新客房信息
     *
     * @param room 客房信息对象
     * @return 成功或失败
     */
    @Override
    public boolean update(Room room) {
        if (roomDao.queryByRid(room.getRid()) == null)
            return false;
        roomDao.update(room);
        return true;
    }

    /**
     * 根据id查询客房信息
     *
     * @param rid 客房id
     * @return 包含一条客房记录的对象
     */
    @Override
    public Room queryByRid(String rid) {
        return roomDao.queryByRid(rid);
    }

    /**
     * 根据客房类型查询客房信息
     *
     * @param rtype 客房类型
     * @return 客房信息列表
     */
    @Override
    public List<Room> queryByRtype(String rtype) {
        return roomDao.queryByRtype(rtype);
    }

    /**
     * 查询全部客房信息
     *
     * @return 客房信息列表
     */
    @Override
    public List<Room> getRooms() {
        return roomDao.getRooms();
    }
}
