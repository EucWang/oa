package cn.wxn.example.webapp.service.impl;

import cn.wxn.example.webapp.AppBaseTest;
import cn.wxn.example.webapp.dto.PrivilegeDto;
import cn.wxn.example.webapp.service.PrivilegeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by wangxn on 2016/9/29.
 */
public class PrivilegeServiceImplTest extends AppBaseTest{

    private Logger logger = Logger.getLogger(PrivilegeServiceImplTest.class);

    @Autowired
    private PrivilegeService privilegeService;

    @Test
    public void testFindPrivilegeById() throws Exception {
        PrivilegeDto privilegeById = privilegeService.findPrivilegeById(2L);

        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info(privilegeById.toString());
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void testFindPrivileges() throws Exception {
        List<PrivilegeDto> privileges = privilegeService.findPrivileges();

        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        for (PrivilegeDto privilegeDto : privileges) {
            logger.info(privilegeDto.toString());
        }
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");

    }

    @Test
    public void testFindRolePrivileges() throws Exception {
        List<PrivilegeDto> rolePrivileges = privilegeService.findRolePrivileges(2L);
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        for (PrivilegeDto privilegeDto : rolePrivileges) {
            logger.info(privilegeDto.toString());
        }
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Test
    public void testConvertPrivilegeToPrivilegeDto() throws Exception {

    }

}