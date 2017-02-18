package com.ican.hotel.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by mrzhou on 17-2-12.
 * 客房Room Entity
 */
@Entity
@Table(name = "Room")
public class Room {
    @Id
    @Column(length = 20)
    private String rid;
    @Column(length = 2)
    private String rfloor;
    @Column(length = 1)
    private String rtype;
    @Column(length = 4)
    private String rprice;
    @Column(length = 1)
    private String rnum;
    @Column
    private String rphoto;
    @Column
    private String other1;
    @Column
    private String ohter2;
    @Column
    private String other3;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRfloor() {
        return rfloor;
    }

    public void setRfloor(String rfloor) {
        this.rfloor = rfloor;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getRprice() {
        return rprice;
    }

    public void setRprice(String rprice) {
        this.rprice = rprice;
    }

    public String getRnum() {
        return rnum;
    }

    public void setRnum(String rnum) {
        this.rnum = rnum;
    }

    public String getRphoto() {
        return rphoto;
    }

    public void setRphoto(String rphoto) {
        this.rphoto = rphoto;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOhter2() {
        return ohter2;
    }

    public void setOhter2(String ohter2) {
        this.ohter2 = ohter2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }
}
