package com.ican.hotel.validation.beans;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import java.util.Date;

/**
 * Created by mrzhou on 17-2-17.
 */
public class ValidateOrder {
    private String rtype;
    @Future(message = "请选择正确的入住日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    @NotEmpty(message = "请选择入住时间")
    private String orderTime;
    @NotEmpty(message = "请输入入住天数")
    private String days;

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
