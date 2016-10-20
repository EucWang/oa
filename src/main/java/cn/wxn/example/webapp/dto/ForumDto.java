package cn.wxn.example.webapp.dto;

import java.io.Serializable;

/**
 * Created by wangxn on 2016/10/20.
 */
public class ForumDto implements Serializable{

    private Long id;

    private String name;

    private String description;

    private Integer position;

    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", position=" + position +
                '}';
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
