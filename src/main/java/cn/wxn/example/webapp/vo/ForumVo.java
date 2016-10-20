package cn.wxn.example.webapp.vo;

import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by wangxn on 2016/10/20.
 */
public class ForumVo implements Serializable {

    private String name;
    private String description;


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
}
