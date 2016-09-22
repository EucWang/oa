package cn.wxn.example.webapp.dao;

import cn.wxn.example.webapp.entry.User;

import java.util.List;

/**
 * Created by wangxn on 2016/9/8.
 */
public interface UserMapper extends ICaptchaMapper {

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(long id);

    User findUserById(long id);

    List<User> findUsers();
}
