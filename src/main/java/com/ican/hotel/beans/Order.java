package com.ican.hotel.beans;

import java.sql.Date;

/**
 * Created by mrzhou on 17-2-12.
 * 订单Order Entiry
 */
public class Order {
    private String oid;
    private String orid;
    private int ouid;
    private Date odate;
    private int odays;
    private int otatol;
    private String other1;
    private String other2;
    private String other3;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public int getOuid() {
        return ouid;
    }

    public void setOuid(int ouid) {
        this.ouid = ouid;
    }

    public Date getOdate() {
        return odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    public int getOdays() {
        return odays;
    }

    public void setOdays(int odays) {
        this.odays = odays;
    }

    public int getOtatol() {
        return otatol;
    }

    public void setOtatol(int otatol) {
        this.otatol = otatol;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }
}
