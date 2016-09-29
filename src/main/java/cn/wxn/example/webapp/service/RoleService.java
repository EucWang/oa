package cn.wxn.example.webapp.service;

import cn.wxn.example.webapp.dto.RoleDto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangxn on 2016/9/12.
 */
//@Service
//@Repository
public interface RoleService {

    boolean insertRole(RoleDto roleDto) throws Exception;

    boolean updateRole(RoleDto roleDto) throws Exception;

    boolean deleteRole(long id) throws Exception;

    RoleDto findRoleById(long id) throws Exception;

    List<RoleDto> findRoles() throws Exception;

}
