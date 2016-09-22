package cn.wxn.example.webapp.service.impl;

import cn.wxn.example.webapp.AppBaseTest;
import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.entry.Department;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * Created by wangxn on 2016/9/22.
 */
public class UserServiceImplTest extends AppBaseTest {

    @Autowired
    private UserService userService;


    @Test
    public void testInsertUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("zhaoyun");
        userDto.setNickname("赵云");
        Department department = new Department();
        department.setId(18L);
        userDto.setDepartment(department);

//        Set<Role> roles = new HashSet<Role>();
//        Role role1 = new Role();
//        Role role2 = new Role();
//        Role role3 = new Role();
//        role1.setId(1L);
//        role2.setId(2L);
//        role3.setId(3L);
//        roles.add(role1);
//        roles.add(role2);
//        roles.add(role3);
//
//        userDto.setRoles(roles);

        boolean b = userService.insertUser(userDto);
        logger.info("================================" + b);
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(8L);
        Set<Role> roles = new HashSet<Role>();
        Role role1 = new Role();
        role1.setId(2L);
        Role role2 = new Role();
        role2.setId(3L);
        roles.add(role1);
        roles.add(role2);
        userDto.setRoles(roles);

        boolean b = userService.updateUser(userDto);
        logger.info("================ " + b);
    }

    @Test
    public void testDeleteUser() throws Exception {
        boolean b = userService.deleteUser(5L);
        logger.info("=================== " + b);
    }

    @Test
    public void testFindUserById() throws Exception {
        UserDto userById = userService.findUserById(8L);
        logger.info(userById + "=========================================");
    }

    @Test
    public void testFindUsers() throws Exception {
        List<UserDto> users = userService.findUsers();
        if (users != null && users.size() > 0) {
            logger.info("=======================");
            for (UserDto userDto : users) {
                logger.info(userDto.toString());
            }
            logger.info("=======================");
        }
    }

}