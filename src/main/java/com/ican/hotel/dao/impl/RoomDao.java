package com.ican.hotel.dao.impl;

import com.ican.hotel.beans.Room;
import com.ican.hotel.dao.IRoomDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by mrzhou on 17-2-13.
 * 客房数据库操作实现类
 */
public class RoomDao implements IRoomDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 插入房间信息到数据库
     *
     * @param room 客房信息对象
     * */
    @Override
    public void add(Room room) {
        sessionFactory.getCurrentSession().save(room);
    }

    /**
     * 从数据库删除一条房间记录（根据rid删除）
     *
     * @param room 客房信息对象
     * */
    @Override
    public void delete(Room room) { sessionFactory.getCurrentSession().delete(room); }

    /**
     * 更新一条客房记录到数据
     *
     * @param room 客房信息对象
     */
    @Override
    public void update(Room room) {
        sessionFactory.getCurrentSession().update(room);
    }

    /**
     * 根据room id查询一条客房数据库记录
     *
     * @param rid 客房id
     * @return 包含一条客房记录的对象
     */
    @Override
    public Room queryByRid(String rid) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Room where rid=?0");
        query.setParameter("0", rid);
        List list = query.list();
        if (list.size() != 0)
            return (Room) list.get(0);
        return null;
    }

    /**
     * 根据客房类型查询一条客房记录
     *
     * @param rtype 客房类型
     * @return 客房信息列表
     */
    @Override
    public List<Room> queryByRtype(String rtype) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Room where rtype=?0");
        query.setParameter("0", rtype);
        return query.list();
    }

    /**
     * 返回全部客房信息
     *
     * @return 客房信息列表
     */
    @Override
    public List<Room> getRooms() {
        return sessionFactory.getCurrentSession().createQuery("from Room").list();
    }
}
