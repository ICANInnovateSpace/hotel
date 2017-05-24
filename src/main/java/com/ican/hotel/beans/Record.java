package com.ican.hotel.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mrzhou on 17-5-6.
 *
 */
@Entity
public class Record {
    private String rid;
    private String ruid;
    private String rrid;
    private Date rdate;
    private String rdays;

    @Id
    @Column(name = "rid")
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "ruid")
    public String getRuid() { return ruid; }

    public void setRuid(String ouid) { this.ruid = ouid; }

    @Basic
    @Column(name = "rrid")
    public String getRrid() { return rrid; }

    public void setRrid(String rrid) { this.rrid = rrid; }

    @Basic
    @Column(name = "rdate")
    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    @Basic
    @Column(name = "rdays")
    public String getRdays() {
        return rdays;
    }

    public void setRdays(String rdays) {
        this.rdays = rdays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (rid != null ? !rid.equals(record.rid) : record.rid != null) return false;
        if (rdate != null ? !rdate.equals(record.rdate) : record.rdate != null) return false;
        if (rdays != null ? !rdays.equals(record.rdays) : record.rdays != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid != null ? rid.hashCode() : 0;
        result = 31 * result + (rdate != null ? rdate.hashCode() : 0);
        result = 31 * result + (rdays != null ? rdays.hashCode() : 0);
        return result;
    }


}
