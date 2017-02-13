package com.ican.hotel.beans;

import com.ican.hotel.validation.ValidGroup_2;
import com.ican.hotel.validation.ValidGroup_1;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.sql.Date;

/**
 * Created by mrzhou on 17-2-9.
 * 用户User Entity
 */
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(length = 11)
    private int uid;
    @NotEmpty(message = "请输入用户名",groups = {ValidGroup_1.class,ValidGroup_2.class})
    @Column(length = 20,unique = true)
    private String uname;
    @Column(length = 20)
    private String ureal;
    @Column(length = 10)
    private String usex;
    @Column
    @Past
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date uborn;
    @NotEmpty(message = "请输入手机号码",groups = {ValidGroup_1.class})
    @Column(length = 20)
    private String uphone;
    @Column(length = 255)
    private String uphoto;
    @Column(length = 18)
    private String ucard;
    @Column(length = 19)
    private String ubank;
    @NotEmpty(message = "请输入密码",groups = {ValidGroup_1.class,ValidGroup_2.class})
    @Column(length = 20)
    private String upsw;
    @Column(length = 1)
    private String ustate;
    @Column(length = 255)
    private String other1;
    @Column(length = 255)
    private String other2;
    @Column(length = 255)
    private String other3;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUreal() {
        return ureal;
    }

    public void setUreal(String ureal) {
        this.ureal = ureal;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public Date getUborn() {
        return uborn;
    }

    public void setUborn(Date uborn) {
        this.uborn = uborn;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUphoto() {
        return uphoto;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto;
    }

    public String getUcard() {
        return ucard;
    }

    public void setUcard(String ucard) {
        this.ucard = ucard;
    }

    public String getUbank() {
        return ubank;
    }

    public void setUbank(String ubank) {
        this.ubank = ubank;
    }

    public String getUpsw() {
        return upsw;
    }

    public void setUpsw(String upsw) {
        this.upsw = upsw;
    }

    public String getUstate() {
        return ustate;
    }

    public void setUstate(String ustate) {
        this.ustate = ustate;
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
