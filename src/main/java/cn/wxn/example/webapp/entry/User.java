package cn.wxn.example.webapp.entry;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by wangxn on 2016/9/8.
 */
public class User implements Serializable{

    private Long id; // u_id

    private String name; // u_name

    private Integer gender; //gender

    private String nickname; //nickname

    private String password; //u_pwd

    private String phoneNumber; // u_phoneNum

    private String email; // u_email

    private String description; // u_description

    private Date registDate; //regist_date

    private String pic; //u_pic

    private Date birthday; //birthday

    private Integer workAge; //work_age

    private User higherUser; //parent_id

    private Set<Role> roles; //  role_user -> ru_id, r_id, u_id

    private Department department;  // d_id

    public Integer getGender() {
        return gender;
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

    public User getHigherUser() {
        return higherUser;
    }

    public void setHigherUser(User higherUser) {
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
        return "User{" +
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
