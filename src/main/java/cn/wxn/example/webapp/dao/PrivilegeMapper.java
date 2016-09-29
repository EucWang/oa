package cn.wxn.example.webapp.dao;

import cn.wxn.example.webapp.entry.Privilege;
import cn.wxn.example.webapp.entry.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangxn on 2016/9/8.
 */
public interface PrivilegeMapper extends ICaptchaMapper {

    int insertPrivilege(Privilege privilege);

    int updatePrivilege(Privilege privilege);

    int deletePrivilege(long id);

    Privilege findPrivilegeById(long id);

    List<Privilege> findPrivileges();

    Privilege findPrivilegeByName(@Param("name") String name);

    //------------------------------------

    List<Privilege> findRolePrivileges(long id);

    boolean insertRolePrivileges(Role role);

    boolean deleteRolePrivileges(Role role);

}
