package cn.wxn.example.webapp.service.impl;

import cn.wxn.example.webapp.dao.RoleMapper;
import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxn on 2016/9/12.
 */
@Service("roleService")
//@WebService
public class RoleServiceImpl implements RoleService {

    private Logger logger = Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    public boolean insertRole(RoleDto roleDto) throws Exception {
        if (roleDto == null) {
            logger.debug("insertRole fail, parameter is null");
            return false;
        }
        Role role = convertRoleDtoToRole(roleDto);
        if (role == null) {
            logger.debug("insertRole fail, convertRoleDtoToRole is fail, get null role object");
            return false;
        }
        try {
            int i = roleMapper.insertRole(role);
            if (i >= 0) {
                return true;
            }
        } catch (Exception ex) {
            logger.warn(ex);
            logger.debug("RoleServiceImpl:insertRole:fail.");
        }
        return false;
    }

    public boolean updateRole(RoleDto roleDto) throws Exception {
        if (roleDto == null) {
            logger.debug("updateRole fail, parameter is null");
            return false;
        }
        Role role = convertRoleDtoToRole(roleDto);
        if (role == null) {
            logger.debug("updateRole fail, convertRoleDtoToRole is fail, get null role object");
            return false;
        }
        try {
            int i = roleMapper.updateRole(role);
            if (i > 0) {
                return true;
            }
        } catch (Exception ex) {
            logger.warn(ex);
            logger.debug("RoleServiceImpl:updateRole:fail.");
        }
        return false;
    }

    public boolean deleteRole(long id) throws Exception {
        if (id >= 0) {
            try {
                int i = roleMapper.deleteRole(id);
                if (i > 0) {
                    return true;
                }
            } catch (Exception ex) {
                logger.warn(ex);
                logger.debug("RoleServiceImpl:updateRole:fail.");
            }
            return false;
        } else {
            logger.debug("RoleServiceImpl:updateRole:parameter is err. id=" + id);
            return false;
        }
    }

    public RoleDto findRoleById(long id) throws Exception {
        Role roleById = roleMapper.findRoleById(id);
        RoleDto roleDto = convertRoleToRoleDto(roleById);
        return roleDto;
    }

    public List<RoleDto> findRoles() throws Exception {
        List<Role> roles = roleMapper.findRoles();
        return convertRolesToRoleDtos(roles);
    }


    private Role convertRoleDtoToRole(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        Role retVal = new Role();
        BeanUtils.copyProperties(roleDto, retVal);
        return retVal;
    }

    private RoleDto convertRoleToRoleDto(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto retVal = new RoleDto();
        BeanUtils.copyProperties(role, retVal);
        return retVal;
    }

    private List<RoleDto> convertRolesToRoleDtos(List<Role> roles) {
        if (roles == null) {
            return null;
        }

        List<RoleDto> roleDtos = new ArrayList<RoleDto>();
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                roleDtos.add(convertRoleToRoleDto(role));
            }
        }
        return roleDtos;
    }

}
