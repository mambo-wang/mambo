package com.wb.wbao.server.permission;

import com.wb.wbao.common.Constant;
import com.wb.wbao.server.role.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = -2906088951634691996L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RESOURCE_TYPE", columnDefinition="enum('menu','button')")
    private String resourceType;

    @Column(name = "URL")
    private String url;

    /**
     * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    @Column(name = "PERMISSION")
    private String permission;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "PARENT_IDS")
    private String parentIds;

    @Column(name = "AVAILABLE")
    private Integer available = Constant.AVAILABLE_FALSE;

    @ManyToMany
    @JoinTable(name = "tbl_role_permission", joinColumns = {@JoinColumn(name = "PERMISSION_ID")},
    inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }


    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
