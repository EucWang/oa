package cn.wxn.example.webapp.dto;

import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.vo.PrivilegeVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private Integer isMenu;

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

    public static List<PrivilegeVo> convertPrivilegesDtoToVo(List<PrivilegeDto> privilegeDtos) throws Exception {
        if (privilegeDtos == null || privilegeDtos.size() == 0) {
            return null;
        }

        List<PrivilegeVo> privilegeVos = new ArrayList<PrivilegeVo>();
        for (PrivilegeDto privilegeDto : privilegeDtos) {
            privilegeVos.add(convertPrivilegeDtoToVo(privilegeDto));
        }
        return privilegeVos;
    }

    public static Set<PrivilegeVo> convertPrivilegesDtoToVo(Set<PrivilegeDto> privilegeDtos) throws Exception {
        if (privilegeDtos == null || privilegeDtos.size() == 0) {
            return null;
        }

        Set<PrivilegeVo> privilegeVos = new HashSet<PrivilegeVo>();
        for (PrivilegeDto privilegeDto : privilegeDtos) {
            privilegeVos.add(convertPrivilegeDtoToVo(privilegeDto));
        }
        return privilegeVos;
    }


    public static PrivilegeVo convertPrivilegeDtoToVo(PrivilegeDto privilegeDto) throws Exception {
        if (privilegeDto == null) {
            return null;
        }

        PrivilegeVo privilegeVo = new PrivilegeVo();
        BeanUtils.copyProperties(privilegeDto, privilegeVo);

        PrivilegeVo parent = new PrivilegeVo();
        if (privilegeDto.getParent() != null) {
            parent.setId(privilegeDto.getParent().getId());
        } else {
            parent = null;
        }

        privilegeVo.setChecked(false);
        privilegeVo.setParent(parent);
        return privilegeVo;
    }

}
