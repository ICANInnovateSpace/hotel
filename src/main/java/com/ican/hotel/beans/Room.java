package com.ican.hotel.beans;

/**
 * Created by mrzhou on 17-2-12.
 * 客房Room Entity
 */
public class Room {
    private String rid;
    private int rfloor;
    private String rtype;
    private int rprice;
    private int rnum;
    private String rphoto;
    private String other1;
    private String ohter2;
    private String other3;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getRfloor() {
        return rfloor;
    }

    public void setRfloor(int rfloor) {
        this.rfloor = rfloor;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public int getRprice() {
        return rprice;
    }

    public void setRprice(int rprice) {
        this.rprice = rprice;
    }

    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
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
