package cn.wxn.example.webapp.dao;

import cn.wxn.example.webapp.AppBaseTest;
import cn.wxn.example.webapp.entry.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangxn on 2016/9/21.
 */
public class UserMapperTest extends AppBaseTest {

    @Autowired
    private UserMapper userMapper;

    private Logger logger = Logger.getLogger(UserMapperTest.class);

    @Test
    public void testInsertUser() throws Exception {
        User user = new User();
        user.setName("daishanchu");
        user.setNickname("岱山初");
//        user.setDescription("王祖青的儿子");

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date birthday = simpleDateFormat.parse("1984-02-01");
//        user.setBirthday(birthday);

//        user.setGender(true);
//        user.setEmail("qqwwdr@163.com");

        user.setPassword("123456");
//        user.setPhoneNumber("13800880009");
//        user.setPic(null);
//        user.setRegistDate(new Date());
//        user.setWorkAge(1);

//        User higherUser = new User();
//        higherUser.setId(0L);
//        user.setHigherUser(higherUser);

//        Department department = new Department();
//        department.setId(2L);
//        user.setDepartment(department);

        userMapper.insertUser(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(5L);
//        user.setName("guanyu");
//        user.setNickname("关羽");
//        user.setDescription("王祖青的儿子");
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date birthday = simpleDateFormat.parse("1984-02-01");
//        user.setBirthday(birthday);
//
//        user.setGender(true);
//        user.setEmail("qqwwdr@163.com");
//
//        user.setPassword("123456");
        User user1 = new User();
        user1.setId(1L);
        user.setHigherUser(user1);

        userMapper.updateUser(user);
    }

    @Test
    public void testDeleteUser() throws Exception {
        int i = userMapper.deleteUser(6L);
        logger.info("testDeleteUser return value is " + i + "  <==================");
    }

    @Test
    public void testFindUserById() throws Exception {
        User userById = userMapper.findUserById(5L);
        logger.info("testFindUserById find user is ::::::");
        logger.info(userById.toString());
    }

    @Test
    public void testFindUsers() throws Exception {
        List<User> users = userMapper.findUsers();
        logger.info("testFindUsers return value <==================");
        for (User u : users) {
            logger.info("::: " + u.toString());
        }
    }
}