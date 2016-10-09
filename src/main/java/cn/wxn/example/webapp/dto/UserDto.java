package cn.wxn.example.webapp.dto;

import cn.wxn.example.webapp.entry.Department;
import cn.wxn.example.webapp.entry.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by wangxn on 2016/9/8.
 */
public class UserDto implements Serializable{

    private Long id;

    private String name;

    private Integer gender;

    private String nickname;

    private String password;

    private String phoneNumber;

    private String email;

    private String description;

    private Date registDate;

    private String pic;

    private Date birthday;

    private Integer workAge;

    private UserDto higherUser;

    private Set<Role> roles;

    private Department department;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public UserDto getHigherUser() {
        return higherUser;
    }

    public void setHigherUser(UserDto higherUser) {
        this.higherUser = higherUser;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", registDate=" + registDate +
                ", pic='" + pic + '\'' +
                ", birthday=" + birthday +
                ", workAge=" + workAge +
                ", higherUser=" + higherUser +
                ", roles=" + roles +
                ", department=" + department +
                '}';
    }
}
