package cn.wxn.example.webapp.vo;

import cn.wxn.example.webapp.entry.Department;
import cn.wxn.example.webapp.utils.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
