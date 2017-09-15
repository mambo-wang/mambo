package com.wb.wbao.server.user;

import com.wb.wbao.server.role.Role;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "SALT")
    private String salt;

    @Column(name = "STATE")
    private Integer state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role", joinColumns = {@JoinColumn(name = "USER_ID")},
    inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    List<Role> roleList;

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public byte[] getCredentialsSalt() {
        return new byte[0];
    }
}
