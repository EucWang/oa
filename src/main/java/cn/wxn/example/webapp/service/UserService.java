package cn.wxn.example.webapp.service;

import cn.wxn.example.webapp.dto.UserDto;

import java.util.List;

/**
 * Created by wangxn on 2016/9/12.
 */
//@Service
//@Repository
public interface UserService {

    boolean insertUser(UserDto userDto) throws Exception;

    boolean updateUser(UserDto userDto) throws Exception;

    boolean deleteUser(long id) throws Exception;

    UserDto findUserById(long id) throws Exception;

    List<UserDto> findUsers() throws Exception;
}
