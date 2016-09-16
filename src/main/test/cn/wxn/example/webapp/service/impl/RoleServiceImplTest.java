package cn.wxn.example.webapp.service.impl;

import cn.wxn.example.webapp.AppBaseTest;
import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.*;

/**
 * Created by wangxn on 2016/9/13.
 */
public class RoleServiceImplTest extends AppBaseTest {

    private  Logger logger = Logger.getLogger("RoleServiceImplTest");

    @Autowired
    private RoleService roleService;

    @Test
    public void testInsertRole() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setName("SoftWareEnginner");
        roleDto.setDescription("软件开发工程师");
        boolean b = roleService.insertRole(roleDto);
        logger.info("testInsertRole result is " + b);
    }

    @Test
    public void testUpdateRole() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(6L);
        roleDto.setDescription("企业内负责技术的最高负责人");
        boolean b = roleService.updateRole(roleDto);
        logger.info("testUpdateRole result is " + b);
    }

    @Test
    public void testDeleteRole() throws Exception {
        boolean b = roleService.deleteRole(5L);
        logger.info("testDeleteRole result is " + b);
    }

    @Test
    public void testFindRoleById() throws Exception {
        RoleDto roleById = roleService.findRoleById(6L);
        if (roleById == null) {
            logger.info("testFindRoleById is fail, return RoleDto is null");
        }else {
            logger.info("testFindRoleById is success, return RoleDto is :" + roleById.toString());
        }
    }

    @Test
    public void testFindRoles() throws Exception {
        List<RoleDto> roles = roleService.findRoles();
        if (roles == null || roles.isEmpty()) {
            logger.info("testFindRoleById is fail, return RoleDto is null");
        }else {
            logger.info("testFindRoleById is success");
            for (RoleDto roleDto : roles) {
               logger.info(roleDto.toString());
            }
        }
    }
}