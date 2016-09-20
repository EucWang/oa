package cn.wxn.example.webapp.dto;

import cn.wxn.example.webapp.entry.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangxn on 2016/9/8.
 */
public class DepartmentDto implements Serializable{

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    //这个属性不属于原有的Department的数据
    private String departmentid;

    private Long id;

    private String name;

    private String description;

    private DepartmentDto parent;

    private List<User> users;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DepartmentDto getParent() {
        return parent;
    }

    public void setParent(DepartmentDto parent) {
        this.parent = parent;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", users=" + users +
                '}';
    }
}
