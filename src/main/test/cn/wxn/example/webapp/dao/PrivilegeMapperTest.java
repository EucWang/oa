package cn.wxn.example.webapp.dao;

import cn.wxn.example.webapp.AppBaseTest;
import cn.wxn.example.webapp.entry.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by wangxn on 2016/9/28.
 */
public class PrivilegeMapperTest extends AppBaseTest {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Test
    public void testInsertPrivilege() throws Exception {


        Privilege privilege = new Privilege();
        privilege.setName("test2");
        Privilege parent = new Privilege();
        parent.setId(0L);
        privilege.setParent(parent);

        privilege.setDescription("test2");
        int i = privilegeMapper.insertPrivilege(privilege);
        logger.info("PrivilegeMapperTest  testInsertPrivilege return value is :::::: " + i);
    }

    @Test
    public void testUpdatePrivilege() throws Exception {
        Privilege privilege = new Privilege();
        privilege.setId(25L);
        privilege.setDescription("testtest");
        privilege.setUrl("test");
        privilege.setName("TEST");
        int i = privilegeMapper.updatePrivilege(privilege);
        logger.info("PrivilegeMapperTest  testUpdatePrivilege return value is :::::: " + i);
    }

    @Test
    public void testDeletePrivilege() throws Exception {
        int i = privilegeMapper.deletePrivilege(25L);
        logger.info("PrivilegeMapperTest  testDeletePrivilege return value is :::::: " + i);

    }

    @Test
    public void testFindPrivilegeById() throws Exception {
        Privilege privilegeById = privilegeMapper.findPrivilegeById(25L);
        logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("PrivilegeMapperTest  testFindPrivilegeById return value is :::::: ");
        logger.info(privilegeById.toString());
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    }

    @Test
    public void testFindPrivileges() throws Exception {

        List<Privilege> privileges = privilegeMapper.findPrivileges();
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("PrivilegeMapperTest  testFindPrivilegeById return value is :::::: ");
        if (privileges != null && privileges.size() > 0) {

            for (Privilege privilege : privileges) {
                logger.info(privilege.toString());
            }
        } else {
            logger.info("NULL");
        }
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    }

    @Test
    public void testFindPrivilegeByName() throws Exception {
        Privilege test = privilegeMapper.findPrivilegeByName("TEST");
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info("PrivilegeMapperTest  testFindPrivilegeById return value is :::::: ");
        if (test != null) {
            logger.info(test.toString());
        } else {
            logger.info("NULL");
        }
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    }

}