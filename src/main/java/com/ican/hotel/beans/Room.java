package com.ican.hotel.beans;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by mrzhou on 17-5-6.
 */
@Entity
public class Room {
    private String rid;
    private String rfloor;
    private String rtype;
    private String rprice;
    private String rnum;
    private String rphoto;

    @Id
    @Column(name = "rid")
    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "rfloor")
    public String getRfloor() {
        return rfloor;
    }

    public void setRfloor(String rfloor) {
        this.rfloor = rfloor;
    }

    @Basic
    @Column(name = "rtype")
    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    @Basic
    @Column(name = "rprice")
    public String getRprice() {
        return rprice;
    }

    public void setRprice(String rprice) {
        this.rprice = rprice;
    }

    @Basic
    @Column(name = "rnum")
    public String getRnum() {
        return rnum;
    }

    public void setRnum(String rnum) {
        this.rnum = rnum;
    }

    @Basic
    @Column(name = "rphoto")
    public String getRphoto() {
        return rphoto;
    }

    public void setRphoto(String rphoto) {
        this.rphoto = rphoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (rid != null ? !rid.equals(room.rid) : room.rid != null) return false;
        if (rfloor != null ? !rfloor.equals(room.rfloor) : room.rfloor != null) return false;
        if (rtype != null ? !rtype.equals(room.rtype) : room.rtype != null) return false;
        if (rprice != null ? !rprice.equals(room.rprice) : room.rprice != null) return false;
        if (rnum != null ? !rnum.equals(room.rnum) : room.rnum != null) return false;
        if (rphoto != null ? !rphoto.equals(room.rphoto) : room.rphoto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid != null ? rid.hashCode() : 0;
        result = 31 * result + (rfloor != null ? rfloor.hashCode() : 0);
        result = 31 * result + (rtype != null ? rtype.hashCode() : 0);
        result = 31 * result + (rprice != null ? rprice.hashCode() : 0);
        result = 31 * result + (rnum != null ? rnum.hashCode() : 0);
        result = 31 * result + (rphoto != null ? rphoto.hashCode() : 0);
        return result;
    }
}
