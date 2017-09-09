package com.wb.wbao.server.user;

import javax.persistence.*;

/**
 * Created by dell on 2017/7/2.
 */
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "COME_YEAR")
    private Integer comeYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getComeYear() {
        return comeYear;
    }

    public void setComeYear(Integer comeYear) {
        this.comeYear = comeYear;
    }
}
