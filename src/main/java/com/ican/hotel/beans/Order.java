package com.ican.hotel.beans;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.util.Date;

/**
 * Created by mrzhou on 17-2-12.
 * 订单Order Entiry
 */
@Entity
@Table(name = "MyOrder")
public class Order {
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(length = 32)
    private String oid;
    @Column(length = 20)
    @NotEmpty(message = "房号不能为空")
    private String orid;
    @Column(length = 32)
    @NotEmpty(message = "用户id不能为空")
    private String ouid;
    @Column
    @Future(message = "请选择正确的入住日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date odate;
    @Column(length = 3)
    @NotEmpty(message = "请输入订房天数")
    private String odays;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date oquit;
    @Column(length = 10)
    private String ototal;
    @Column
    private String other1;
    @Column
    private String other2;
    @Column
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

    public String getOuid() {
        return ouid;
    }

    public void setOuid(String ouid) {
        this.ouid = ouid;
    }

    public Date getOdate() {
        return odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    public String getOdays() {
        return odays;
    }

    public void setOdays(String odays) {
        this.odays = odays;
    }

    public Date getOquit() { return oquit; }

    public void setOquit(Date oquit) { this.oquit = oquit; }

    public String getOtotal() {
        return ototal;
    }

    public void setOtotal(String ototal) {
        this.ototal = ototal;
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
