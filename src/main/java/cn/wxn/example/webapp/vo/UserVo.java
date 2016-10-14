package cn.wxn.example.webapp.vo;

import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.entry.Department;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.utils.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

/**
 * Created by wangxn on 2016/10/9.
 */
public class UserVo {

    private String id;

    private String name;

    private Integer gender;

    private String nickname;

    private String password;

    private String phoneNum;

    private String email;

    private String description;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private String department;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Role> getUserRoles(){
        Set<Role> retVal = null;
        if (!StringUtils.isNullOrEmpty(role)) {
            List<String> strings = StringUtils.split2List(role, ",");
            if (strings != null && strings.size() > 0) {
                retVal = new HashSet<Role>();
                for (String roleIdS : strings) {
                    Long roleIdL = Long.valueOf(roleIdS);
                    Role role = new Role();
                    role.setId(roleIdL);
                    retVal.add(role);
                }
            }
        }
        return retVal;
    }

    /**
     * 将UserVo转换成UserDto的操作
     * @param
     * @return
     */
    public UserDto convertUserVoToUserDto() {
        UserDto userDto = new UserDto();
        if (!StringUtils.isNullOrEmpty(getId())) {
            Long userIdL = Long.valueOf(getId());
            userDto.setId(userIdL);
        }
        userDto.setName(getName());
        userDto.setNickname(getNickname());
        userDto.setGender(getGender());
        userDto.setPassword(getPassword());
        userDto.setPhoneNumber(getPhoneNum());
        userDto.setEmail(getEmail());
        userDto.setDescription(getDescription());
        userDto.setBirthday(getBirthday());
        userDto.setDepartment(convertToEmptyDepartment());
        userDto.setRoles(getUserRoles());
        return userDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Department convertToEmptyDepartment(){
        Department department = null;
        if (!StringUtils.isBlank(this.getDepartment())){
            department = new Department();
            Long aLong = Long.valueOf(this.getDepartment());
            if (aLong != null) {
                department.setId(aLong);
            }
        }
        return department;
    }
}
