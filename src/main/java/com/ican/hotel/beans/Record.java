package com.ican.hotel.beans;

import java.sql.Date;

/**
 * Created by mrzhou on 17-2-12.
 * 入住记录Record Entity
 */
public class Record {
    private String rid;
    private int ruid;
    private String rrid;
    private Date rdate;
    private int rdays;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getRrid() {
        return rrid;
    }

    public void setRrid(String rrid) {
        this.rrid = rrid;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public int getRdays() {
        return rdays;
    }

    public void setRdays(int rdays) {
        this.rdays = rdays;
    }
}
