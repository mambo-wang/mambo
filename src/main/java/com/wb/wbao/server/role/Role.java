package com.wb.wbao.server.role;

import com.wb.wbao.common.Constant;
import com.wb.wbao.server.permission.Permission;
import com.wb.wbao.server.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -2179792193919468821L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AVAILABLE")
    private Integer available = Constant.AVAILABLE_FALSE;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_role_permission",joinColumns = {@JoinColumn(name = "ROLE_ID")},
    inverseJoinColumns = {@JoinColumn(name = "PERMISSION_ID")})
    private List<Permission> permissions;

    @ManyToMany
    @JoinTable(name = "tbl_user_role", joinColumns = {@JoinColumn(name = "ROLE_ID")},
    inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
