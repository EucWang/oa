package cn.wxn.example.webapp.dto;

import cn.wxn.example.webapp.entry.Role;

import java.util.List;

/**
 * Created by wangxn on 2016/9/28.
 */
public class PrivilegeDto {

    private Long id;

    private PrivilegeDto parent;

    private String name;

    private String description;

    private String url;

    private String icon;

    private List<Role> roles; //一对多

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrivilegeDto getParent() {
        return parent;
    }

    public void setParent(PrivilegeDto parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", roles=" + roles +
                '}';
    }
}