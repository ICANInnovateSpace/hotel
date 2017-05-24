package com.ican.hotel.beans;

import com.ican.hotel.validation.ValidGroup_1;
import com.ican.hotel.validation.ValidGroup_2;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by mrzhou on 17-5-6.
 */
@Entity
public class User {
    private String uid;
    private String uname;
    private String ureal;
    private String usex;
    private Date uborn;
    private String uphone;
    private String uphoto;
    private String ucard;
    private String ubank;
    private String upsw;
    private String ustate;
    private List<Order> orders;
    private List<Record> records;

    @Id
    @Column(name = "uid")
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "uname",unique = true)
    @NotEmpty(message = "用户名不能为空",groups = {ValidGroup_1.class,ValidGroup_2.class})
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "ureal")
    public String getUreal() {
        return ureal;
    }

    public void setUreal(String ureal) {
        this.ureal = ureal;
    }

    @Basic
    @Column(name = "usex")
    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    @Basic
    @Column(name = "uborn")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getUborn() {
        return uborn;
    }

    public void setUborn(Date uborn) {
        this.uborn = uborn;
    }

    @Basic
    @Column(name = "uphone")
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$",message = "请输入正确的手机号",groups = {ValidGroup_1.class})
    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    @Basic
    @Column(name = "uphoto")
    public String getUphoto() {
        return uphoto;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto;
    }

    @Basic
    @Column(name = "ucard")
    public String getUcard() {
        return ucard;
    }

    public void setUcard(String ucard) {
        this.ucard = ucard;
    }

    @Basic
    @Column(name = "ubank")
    public String getUbank() {
        return ubank;
    }

    public void setUbank(String ubank) {
        this.ubank = ubank;
    }

    @Basic
    @Column(name = "upsw")
    @Length(min=6, max=20,message = "密码长度必须在{min}到{max}之间",groups = {ValidGroup_1.class,ValidGroup_2.class})
    public String getUpsw() {
        return upsw;
    }

    public void setUpsw(String upsw) {
        this.upsw = upsw;
    }

    @Basic
    @Column(name = "ustate")
    public String getUstate() {
        return ustate;
    }

    public void setUstate(String ustate) {
        this.ustate = ustate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (uid != null ? !uid.equals(user.uid) : user.uid != null) return false;
        if (uname != null ? !uname.equals(user.uname) : user.uname != null) return false;
        if (ureal != null ? !ureal.equals(user.ureal) : user.ureal != null) return false;
        if (usex != null ? !usex.equals(user.usex) : user.usex != null) return false;
        if (uborn != null ? !uborn.equals(user.uborn) : user.uborn != null) return false;
        if (uphone != null ? !uphone.equals(user.uphone) : user.uphone != null) return false;
        if (uphoto != null ? !uphoto.equals(user.uphoto) : user.uphoto != null) return false;
        if (ucard != null ? !ucard.equals(user.ucard) : user.ucard != null) return false;
        if (ubank != null ? !ubank.equals(user.ubank) : user.ubank != null) return false;
        if (upsw != null ? !upsw.equals(user.upsw) : user.upsw != null) return false;
        if (ustate != null ? !ustate.equals(user.ustate) : user.ustate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (ureal != null ? ureal.hashCode() : 0);
        result = 31 * result + (usex != null ? usex.hashCode() : 0);
        result = 31 * result + (uborn != null ? uborn.hashCode() : 0);
        result = 31 * result + (uphone != null ? uphone.hashCode() : 0);
        result = 31 * result + (uphoto != null ? uphoto.hashCode() : 0);
        result = 31 * result + (ucard != null ? ucard.hashCode() : 0);
        result = 31 * result + (ubank != null ? ubank.hashCode() : 0);
        result = 31 * result + (upsw != null ? upsw.hashCode() : 0);
        result = 31 * result + (ustate != null ? ustate.hashCode() : 0);
        return result;
    }


    @OneToMany
    @JoinColumn(name = "ouid")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @OneToMany
    @JoinColumn(name = "ruid")
    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
