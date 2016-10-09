package cn.wxn.example.webapp.service.impl;

import cn.wxn.example.webapp.dao.UserMapper;
import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.entry.Department;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.entry.User;
import cn.wxn.example.webapp.service.UserService;
import cn.wxn.example.webapp.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wangxn on 2016/9/12.
 */
@Service("userService")
//@WebService
@Transactional
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    public boolean insertUser(UserDto userDto) throws Exception {
        if (userDto == null) {
            logger.debug("insertUser fail, parameter is null");
            return false;
        }
        User user = convertUserDtoToUser(userDto);
        if (user == null) {
            logger.debug("insertUser fail, convertUserDtoToUser is fail, get null User object");
            return false;
        }
        try {

            User userByName = userMapper.findUserByName(user.getName());
            if (userByName != null) {
                logger.info("insertUser fail, the username already exists.");
                return  false;
            }

            int i = userMapper.insertUser(user);
            if (i >= 0) {
                Integer insertUserRoles = -1;
                if (user.getRoles() != null && user.getRoles().size() > 0) {
                    insertUserRoles = userMapper.insertUserRole(user);
                } else {
                    insertUserRoles = 0;
                    logger.info("user have no role.");
                }
                if (insertUserRoles >= 0) {
                    return true;
                } else {
                    logger.debug("insert user's roles to role_user fail.");
                }
            } else {
                logger.debug("insert user to user table fail");
            }
        } catch (Exception ex) {
            logger.warn(ex);
            logger.debug("UserServiceImpl:insertUser:fail.");
        }
        return false;
    }

    public boolean updateUser(UserDto userDto) throws Exception {
        if (userDto == null) {
            logger.debug("updateUser fail, parameter is null");
            return false;
        }
        User user = convertUserDtoToUser(userDto);
        if (user == null) {
            logger.debug("updateUser fail, convertUserDtoToUser is fail, get null User object");
            return false;
        }
        try {

            int i = -1;
            if (!StringUtils.isBlank(user.getName())) {
                i = userMapper.updateUser(user);
            } else {
                logger.info("udpate user but maybe update nothing.");
                i = 0;
            }

            if (i >= 0) {
                if (user.getRoles() != null && user.getRoles().size() > 0) {
                    List<Role> userRolesByUserId = userMapper.findUserRolesByUserId(user.getId());
                    if (userRolesByUserId != null) {
                        Set<Role> roles = new HashSet<Role>();
                        roles.addAll(userRolesByUserId);

                        Set<Role> needInsertRoles = new HashSet<Role>();
                        Set<Role> needDeleteRoles = new HashSet<Role>();
                        compareUserRoles(roles, user.getRoles(), needInsertRoles, needDeleteRoles);

                        if (needInsertRoles.size() > 0) {
                            User userInsert = new User();
                            userInsert.setId(user.getId());
                            userInsert.setRoles(needInsertRoles);
                            Integer integer = userMapper.insertUserRole(userInsert);
                            if (integer != null) {
                                logger.info("update user' roles , need to insert user's roles success");
                            }
                        } else {
                            logger.info("update user' roles ,  do not need to update user's roles.");
                        }

                        if (needDeleteRoles.size() > 0) {
                            User userDelete = new User();
                            userDelete.setId(user.getId());
                            userDelete.setRoles(needDeleteRoles);
                            Integer integer = userMapper.deleteUserRolesByUserIdAndRoleId(userDelete);
                            if (integer != null) {
                                logger.info("update user' roles , need to delete user's roles success");
                            }
                        } else {
                            logger.info("update user' roles , do not need to delete user's roles");
                        }
                    } else {
                        Integer integer = userMapper.insertUserRole(user);
                        if (integer != null) {
                            logger.info("update user roles success.");
                        } else {
                            logger.info("update user roles fail.");
                        }
                    }
                    return true;
                } else {
                    logger.info("update user, user have no role.");
                    return true;
                }
            }
        } catch (Exception ex) {
            logger.warn(ex);
            logger.debug("UserServiceImpl:updateUser:fail.");
        }
        return false;
    }


    public boolean deleteUser(long id) throws Exception {
        if (id >= 0) {
            try {
                int i = userMapper.deleteUser(id);
                if (i > 0) {
                    Integer integer = userMapper.deleteUserRolesByUserId(id);
                    if (integer == null) {
                        logger.info("delete user's roles from role_user fail.");
                    }

                    Integer userChildrenCount = userMapper.findUserChildrenCount(id);
                    if (userChildrenCount != null && userChildrenCount > 0) {
                        Integer integer1 = userMapper.setUserChildrenParentNull(id);
                        if (integer1 != null) {
                            logger.info("set the user 's children 's parent id is 0");
                        }
                    }

                    return true;
                }
            } catch (Exception ex) {
                logger.warn(ex);
                logger.debug("UserServiceImpl:updateUser:fail.");
            }
            return false;
        } else {
            logger.debug("UserServiceImpl:updateUser:parameter is err. id=" + id);
            return false;
        }
    }

    public UserDto findUserById(long id) throws Exception {
        User userById = userMapper.findUserById(id);

        List<Role> userRolesByUserId = userMapper.findUserRolesByUserId(id);
        if (userRolesByUserId != null && userRolesByUserId.size() > 0) {
            Set<Role> roles = new HashSet<Role>();
            roles.addAll(userRolesByUserId);
            userById.setRoles(roles);
        }

        UserDto userDto = convertUserToUserDto(userById);
        return userDto;
    }

    public List<UserDto> findUsers() throws Exception {
        List<User> Users = userMapper.findUsers();
        return convertUsersToUserDtos(Users);
    }


    private User convertUserDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User retVal = new User();
        BeanUtils.copyProperties(userDto, retVal);

        Set<Role> roles = userDto.getRoles();
        if (roles != null && roles.size() > 0) {
            retVal.setRoles(roles);
        }

        Department department = userDto.getDepartment();
        if (department != null) {
            retVal.setDepartment(department);
        }

        UserDto higherUser = userDto.getHigherUser();
        if (higherUser != null) {
            User user = convertUserDtoToUser(higherUser);
            if (user != null) {
                retVal.setHigherUser(user);
            }
        }

        return retVal;
    }

    private UserDto convertUserToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto retVal = new UserDto();
        BeanUtils.copyProperties(user, retVal);

        Set<Role> roles = user.getRoles();
        if (roles != null && roles.size() > 0) {
            retVal.setRoles(roles);
        }

        Department department = user.getDepartment();
        if (department != null) {
            retVal.setDepartment(department);
        }

        User higherUser = user.getHigherUser();
        if (higherUser != null) {
            UserDto userDto = convertUserToUserDto(higherUser);
            if (userDto != null) {
                retVal.setHigherUser(userDto);
            }
        }

        return retVal;
    }

    private List<UserDto> convertUsersToUserDtos(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserDto> userDtos = new ArrayList<UserDto>();
        if (users != null && users.size() > 0) {
            for (User user : users) {
                userDtos.add(convertUserToUserDto(user));
            }
        }
        return userDtos;
    }

    /**
     * @param rolesLeft       数据库中已经有的
     * @param rolesRight      可能需要更新的
     * @param needInsertRoles 最后的结果,需要插入的
     * @param needDeleteRoles 最后的结果,需要从数据库中删除的
     *                        有多的,需要插入
     *                        有少了的,需要删除
     */
    private void compareUserRoles(Set<Role> rolesLeft, Set<Role> rolesRight, Set<Role> needInsertRoles, Set<Role> needDeleteRoles) {
        for (Role role : rolesLeft) {
            if (!rolesRight.contains(role)) {
                needDeleteRoles.add(role); //需要从数据库中删除的
            }
        }
        for (Role role : rolesRight) {
            if (!rolesLeft.contains(role)) {
                needInsertRoles.add(role);  //需要插入到数据库中的
            }
        }
    }

}
