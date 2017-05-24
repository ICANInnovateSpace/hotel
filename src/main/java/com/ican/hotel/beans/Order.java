package com.ican.hotel.beans;

import com.ican.hotel.validation.ValidGroup_1;
import com.ican.hotel.validation.ValidGroup_3;
import com.ican.hotel.validation.ValidGroup_4;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by mrzhou on 17-5-6.
 */
@Entity
@Table(name = "MyOrder", schema = "hotel", catalog = "")
public class Order {
    private String oid;
    private String ouid;
    private Date odate;
    private String odays;
    private Date oquit;
    private String ototal;
    private String wxNO;
    private String wxPayUrl;
    private Room room;

    @Id
    @Column(name = "oid")
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "ouid")
    @NotBlank(message = "用户id不能为空",groups = {ValidGroup_3.class,ValidGroup_4.class})
    public String getOuid() { return ouid; }

    public void setOuid(String ouid) { this.ouid = ouid; }

    @Basic
    @Column(name = "odate")
    @Future(message = "请选择正确的入住日期",groups = {ValidGroup_3.class,ValidGroup_4.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getOdate() {
        return odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    @Basic
    @Column(name = "odays")
    @Pattern(regexp = "^\\d+$",message = "请输入正确的入住天数",groups = {ValidGroup_3.class,ValidGroup_4.class})
    public String getOdays() {
        return odays;
    }

    public void setOdays(String odays) {
        this.odays = odays;
    }

    @Basic
    @Column(name = "oquit")
    @Future(message = "请选择正确的离店日期",groups = {ValidGroup_4.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getOquit() {
        return oquit;
    }

    public void setOquit(Date oquit) {
        this.oquit = oquit;
    }

    @Basic
    @Column(name = "ototal")
    public String getOtotal() {
        return ototal;
    }

    public void setOtotal(String ototal) {
        this.ototal = ototal;
    }

    @Basic
    @Column(name = "wxNO")
    @NotBlank(message = "微信单号不能为空",groups = {ValidGroup_4.class})
    public String getWxNO() {
        return wxNO;
    }

    public void setWxNO(String wxNO) {
        this.wxNO = wxNO;
    }

    @Basic
    @Column(name = "wxPayUrl")
    @NotBlank(message = "扫码支付url不能为空",groups = {ValidGroup_4.class})
    public String getWxPayUrl() {
        return wxPayUrl;
    }

    public void setWxPayUrl(String wxPayUrl) {
        this.wxPayUrl = wxPayUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (oid != null ? !oid.equals(order.oid) : order.oid != null) return false;
        if (odate != null ? !odate.equals(order.odate) : order.odate != null) return false;
        if (odays != null ? !odays.equals(order.odays) : order.odays != null) return false;
        if (oquit != null ? !oquit.equals(order.oquit) : order.oquit != null) return false;
        if (ototal != null ? !ototal.equals(order.ototal) : order.ototal != null) return false;
        if (wxNO != null ? !wxNO.equals(order.wxNO) : order.wxNO != null) return false;
        if (wxPayUrl != null ? !wxPayUrl.equals(order.wxPayUrl) : order.wxPayUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid != null ? oid.hashCode() : 0;
        result = 31 * result + (odate != null ? odate.hashCode() : 0);
        result = 31 * result + (odays != null ? odays.hashCode() : 0);
        result = 31 * result + (oquit != null ? oquit.hashCode() : 0);
        result = 31 * result + (ototal != null ? ototal.hashCode() : 0);
        result = 31 * result + (wxNO != null ? wxNO.hashCode() : 0);
        result = 31 * result + (wxPayUrl != null ? wxPayUrl.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "orid")
    @NotNull(message = "客房不能为空",groups = {ValidGroup_4.class})
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
