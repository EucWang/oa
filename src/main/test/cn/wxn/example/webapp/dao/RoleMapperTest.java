package cn.wxn.example.webapp.dao;

import cn.wxn.example.webapp.AppBaseTest;
import cn.wxn.example.webapp.entry.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by wangxn on 2016/9/12.
 */
public class RoleMapperTest extends AppBaseTest {

    private Logger logger = Logger.getLogger("RoleMapperTest");

    @Autowired
    private RoleMapper roleMapper;

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testInsertRole() throws Exception {
        Role role = new Role();
        role.setName("po");
        role.setDescription("product operator");
        roleMapper.insertRole(role);
    }

    @Test
    public void testUpdateRole() throws Exception {
        Role role = new Role();
        role.setId(2L);
        role.setName("QA");
        role.setDescription("Software Development Engineer in Test ");
        int i = roleMapper.updateRole(role);
        logger.info("testUpdateRole result i = " + i);
    }

    @Test
    public void testDeleteRole() throws Exception {
        int i = roleMapper.deleteRole(4L);
        logger.info("testDeleteRole result i = " + i);
    }

    @Test
    public void testFindRoleById() throws Exception {
        Role roleById = roleMapper.findRoleById(3L);
        if (roleById != null)
            logger.info("testDeleteRole result Role = " + roleById.toString());
        else
            logger.info("testDeleteRole result Role is null ");
    }

    @Test
    public void testFindRoles() throws Exception {
        List<Role> roles = roleMapper.findRoles();
        if (roles != null && roles.size() > 0 ){
            logger.info("testFindRoles result is :");
            for (Role role : roles) {
                logger.info(role.toString());
            }
        }else  {
            logger.info("testFindRoles result is null or list is empty.");
        }

    }

}