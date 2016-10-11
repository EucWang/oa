package cn.wxn.example.webapp.entry;

import java.util.List;

/**
 * Created by wangxn on 2016/9/28.
 */
public class Privilege {

    private Long id;

    private Privilege parent;

    private String name;

    private String description;

    private String url;

    private String icon;

    private Integer isMenu;

    private List<Role> roles; //一对多

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

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

    public Privilege getParent() {
        return parent;
    }

    public void setParent(Privilege parent) {
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
                ", isMenu=" + isMenu +
                ", roles=" + roles +
                '}';
    }
}
