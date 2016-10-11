package cn.wxn.example.webapp.service;

import cn.wxn.example.webapp.dto.PrivilegeDto;
import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.vo.PrivilegeVo;

import java.util.List;

/**
 * Created by wangxn on 2016/9/28
 */
public interface PrivilegeService {

    PrivilegeDto findPrivilegeById(long id) throws Exception;

    List<PrivilegeDto> findPrivileges() throws Exception;

    List<PrivilegeDto> findRolePrivileges(long roleid) throws Exception;

    boolean updateRolePrivileges(RoleDto roleDto) throws Exception;

    List<PrivilegeVo> findFullRootPrivileges() throws Exception;
}
