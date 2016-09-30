package cn.wxn.example.webapp.vo;

import java.util.List;

/**
 * Created by wangxn on 2016/9/28.
 */
public class PrivilegeVo {

    private Long id;

    private PrivilegeVo parent;

    private String name;

    private String description;

    private String url;

    private String icon;

    private boolean checked;

    private int level;

    private List<PrivilegeVo> children;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<PrivilegeVo> getChildren() {
        return children;
    }

    public void setChildren(List<PrivilegeVo> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrivilegeVo getParent() {
        return parent;
    }

    public void setParent(PrivilegeVo parent) {
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

}
