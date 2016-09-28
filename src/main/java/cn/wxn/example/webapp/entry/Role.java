package cn.wxn.example.webapp.entry;

import org.springframework.stereotype.Component;

import java.io.LineNumberReader;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wangxn on 2016/9/8.
 */
@Component
public class Role implements Serializable {

    private Long id;

    private String name;

    private String description;

    private List<Privilege> privileges; //一对多

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
