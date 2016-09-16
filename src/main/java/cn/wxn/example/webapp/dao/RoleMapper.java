package cn.wxn.example.webapp.dao;

import cn.wxn.example.webapp.entry.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangxn on 2016/9/8.
 */
public interface RoleMapper extends ICaptchaMapper {

    int insertRole(Role role);

    int updateRole(Role role);

    int deleteRole(long id);

    Role findRoleById(long id);

    List<Role> findRoles();
}
