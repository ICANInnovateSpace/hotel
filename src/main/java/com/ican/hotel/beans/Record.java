package com.ican.hotel.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mrzhou on 17-2-12.
 * 入住记录Record Entity
 */
@Entity
@Table(name = "Record")
public class Record {
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(length = 32)
    private String rid;
    @Column(length = 32)
    private String ruid;
    @Column(length = 20)
    private String rrid;
    @Column
    private Date rdate;
    @Column(length = 3)
    private String rdays;
    @Column
    private String other1;
    @Column
    private String other2;
    @Column
    private String other3;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRuid() {
        return ruid;
    }

    public void setRuid(String ruid) {
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

    public String getRdays() {
        return rdays;
    }

    public void setRdays(String rdays) {
        this.rdays = rdays;
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
