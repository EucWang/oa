package cn.wxn.example.webapp.service.impl;

import cn.wxn.example.webapp.dao.PrivilegeMapper;
import cn.wxn.example.webapp.dao.RoleMapper;
import cn.wxn.example.webapp.dto.PrivilegeDto;
import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.entry.Department;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.entry.Privilege;
import cn.wxn.example.webapp.exception.ParamFailException;
import cn.wxn.example.webapp.service.PrivilegeService;
import cn.wxn.example.webapp.utils.StringUtils;
import cn.wxn.example.webapp.vo.PrivilegeVo;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wangxn on 2016/9/12.
 */
@Service("privilegeService")
//@WebService
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

    private Logger logger = Logger.getLogger(PrivilegeServiceImpl.class);

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private RoleMapper roleMapper;

    public PrivilegeDto findPrivilegeById(long id) throws Exception {
        Privilege privilegeById = privilegeMapper.findPrivilegeById(id);
        return convertPrivilegeToPrivilegeDto(privilegeById);
    }

    public List<PrivilegeDto> findPrivileges() throws Exception {
        List<Privilege> privileges = privilegeMapper.findPrivileges();
        return convertPrivilegesToPrivilegeDtos(privileges);
    }



    /**
     * 获取全部权限,并且 设置好权限之间的上下级关系
     * @return
     * @throws Exception
     */
    public List<PrivilegeVo> findFullRootPrivileges() throws  Exception {

        //获取所有权限
        List<PrivilegeDto> privileges = findPrivileges();
        List<PrivilegeVo> privilegeVos = PrivilegeDto.convertPrivilegesDtoToVo(privileges);

        //建立权限之间的父子关系,并且设置上层级属性
        for (int i = 0; i < privilegeVos.size(); i++) {
            PrivilegeVo privilegeVoI = privilegeVos.get(i);
            if (privilegeVoI.getParent() == null || privilegeVoI.getParent().getId() == 0) {
                privilegeVoI.setParent(null);
                privilegeVoI.setLevel(0);
            }
            for (int j = 0; j < privilegeVos.size(); j++) {
                if (j == i) {
                    continue;
                }
                PrivilegeVo privilegeVoJ = privilegeVos.get(j);
                if (privilegeVoJ.getParent() != null && privilegeVoJ.getParent().getId() != 0) {
                    if (privilegeVoJ.getParent().getId() == privilegeVoI.getId()) {
                        privilegeVoJ.setParent(privilegeVoI);
                        if (privilegeVoI.getChildren() == null) {
                            privilegeVoI.setChildren(new ArrayList<PrivilegeVo>());
                        }
                        privilegeVoI.getChildren().add(privilegeVoJ);
                        privilegeVoJ.setLevel(privilegeVoI.getLevel() + 1);
                    }
                }
            }
        }

//        LinkedList<PrivilegeVo> privilegeVos1 = new LinkedList<PrivilegeVo>();
//        privilegeVos1.addAll(privilegeVos);
//        //对权限进行排序,方便界面显示
//        sortPrvilegeVos(privilegeVos1, 0, 0);

        LinkedList<PrivilegeVo> privilegeVos1 = new LinkedList<PrivilegeVo>();
        for (PrivilegeVo privilegeVo : privilegeVos) {
            if (privilegeVo.getParent() == null) {
                privilegeVos1.add(privilegeVo);
            }
        }

        return privilegeVos1;
    }

    public List<PrivilegeDto> findRolePrivileges(long roleid) throws Exception {
        List<Privilege> rolePrivileges = privilegeMapper.findRolePrivileges(roleid);
        return convertPrivilegesToPrivilegeDtos(rolePrivileges);
    }


    public boolean updateRolePrivileges(RoleDto roleDto) throws Exception {
        if (roleDto == null) {
            logger.info("PrivilegeServiceImpl -> updateRolePrivileges -> roleDto is null");
            throw new ParamFailException("PrivilegeServiceImpl -> updateRolePrivileges -> roleDto is null");
        }
        Role role = convertRoleDtoToRole(roleDto);

        List<Privilege> rolePrivilegesInDb = privilegeMapper.findRolePrivileges(role.getId());
        List<Privilege> rolePrivilegesInWeb = role.getPrivileges();


        if ((rolePrivilegesInDb == null || rolePrivilegesInDb.size() == 0)
                && (rolePrivilegesInWeb == null || rolePrivilegesInWeb.size() == 0)) {
            logger.info("PrivilegeServiceImpl -> updateRolePrivileges -> do not need update role's privileges. no change");
            return false;
        }

        //只需要插入的情况
        if ((rolePrivilegesInDb == null || rolePrivilegesInDb.size() == 0)
                && (rolePrivilegesInWeb != null || rolePrivilegesInWeb.size() > 0)) {
            return privilegeMapper.insertRolePrivileges(role);
        }

        //只需要删除的情况
        if ((rolePrivilegesInDb != null || rolePrivilegesInDb.size() > 0)
                && (rolePrivilegesInWeb == null || rolePrivilegesInWeb.size() == 0)) {
            role.setPrivileges(rolePrivilegesInDb);
            return privilegeMapper.deleteRolePrivileges(role);
        }

        List<Privilege> needInsertPrivileges = new ArrayList<Privilege>();
        List<Privilege> needDeletePrivileges = new ArrayList<Privilege>();
        compareRolePrivileges(rolePrivilegesInDb, rolePrivilegesInWeb, needInsertPrivileges, needDeletePrivileges);
        logger.info("PrivilegeServiceImpl -> updateRolePrivileges -> needInsertPrivileges:");
        for (Privilege p : needInsertPrivileges) {
            logger.info(p.getId());
        }
        logger.info("PrivilegeServiceImpl -> updateRolePrivileges -> needDeletePrivileges:");
        for (Privilege p : needDeletePrivileges) {
            logger.info(p.getId());
        }

        boolean isInsertRolePrivilegesSuccess = true;
        if (needInsertPrivileges.size() > 0) {
            role.setPrivileges(null);
            role.setPrivileges(needInsertPrivileges);
            isInsertRolePrivilegesSuccess = privilegeMapper.insertRolePrivileges(role);
        }

        boolean isDeleteRolePrivilegesSuccess = true;
        if (needDeletePrivileges.size() > 0) {
            role.setPrivileges(null);
            role.setPrivileges(needDeletePrivileges);
             isDeleteRolePrivilegesSuccess = privilegeMapper.deleteRolePrivileges(role);
        }

        return isInsertRolePrivilegesSuccess && isDeleteRolePrivilegesSuccess;
    }

    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------

    private PrivilegeDto convertPrivilegeToPrivilegeDto(Privilege privilege) {
        if (privilege == null) {
            return null;
        }
        PrivilegeDto retVal = new PrivilegeDto();
        BeanUtils.copyProperties(privilege, retVal);

        Privilege parent = privilege.getParent();
        if (parent != null) {
            PrivilegeDto parentDto = convertPrivilegeToPrivilegeDto(parent);
            retVal.setParent(parentDto);
        }

        return retVal;
    }

    private List<PrivilegeDto> convertPrivilegesToPrivilegeDtos(List<Privilege> Privileges) {
        if (Privileges == null) {
            return null;
        }

        List<PrivilegeDto> PrivilegeDtos = new ArrayList<PrivilegeDto>();
        if (Privileges != null && Privileges.size() > 0) {
            for (Privilege Privilege : Privileges) {
                PrivilegeDtos.add(convertPrivilegeToPrivilegeDto(Privilege));
            }
        }
        return PrivilegeDtos;
    }

    /**
     * @param privilegesLeft       数据库中已经有的
     * @param privilegesRight      可能需要更新的
     * @param needInsertPrivileges 最后的结果,需要插入的
     * @param needDeletePrivileges 最后的结果,需要从数据库中删除的
     *                             有多的,需要插入
     *                             有少了的,需要删除
     */
    private void compareRolePrivileges(List<Privilege> privilegesLeft, List<Privilege> privilegesRight, List<Privilege> needInsertPrivileges, List<Privilege> needDeletePrivileges) {
        for (Privilege privilege : privilegesLeft) {
            if (!hasPrivilegeInList(privilegesRight, privilege)) {
                needDeletePrivileges.add(privilege); //需要从数据库中删除的
            }
        }
        for (Privilege privilege : privilegesRight) {
            if (!hasPrivilegeInList(privilegesLeft, privilege)) {
                needInsertPrivileges.add(privilege);  //需要插入到数据库中的
            }
        }
    }

    private boolean hasPrivilegeInList(List<Privilege> privileges, Privilege privilege) {
        if (privilege == null || privilege.getId() == null || privileges == null || privileges.size() == 0) {
            return false;
        }

        for (Privilege p : privileges) {
            if (p.getId() != null && p.getId() == privilege.getId()) {
                return true;
            }
        }
        return false;
    }

    private Role convertRoleDtoToRole(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        Role retVal = new Role();
        BeanUtils.copyProperties(roleDto, retVal);

        List<Privilege> privileges = roleDto.getPrivileges();
        if (privileges != null && privileges.size() > 0) {
            retVal.setPrivileges(privileges);
        }
        return retVal;
    }
}
