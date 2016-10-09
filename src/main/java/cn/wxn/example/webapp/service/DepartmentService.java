package cn.wxn.example.webapp.service;

import cn.wxn.example.webapp.dto.DepartmentDto;
import cn.wxn.example.webapp.dto.RoleDto;

import java.util.List;

/**
 * Created by wangxn on 2016/9/12.
 */
//@Service
//@Repository
public interface DepartmentService {

    boolean insertDepartment(DepartmentDto departmentDto) throws Exception;

    boolean updateDepartment(DepartmentDto departmentDto) throws Exception;

    boolean deleteDepartment(long id) throws Exception;

    DepartmentDto findDepartmentById(long id) throws Exception;

    /**
     *
     * @param pid  如果取值-1,表示获取所有的部门,如果大于等于0,则,获取给定部门的子部门
     * @return
     * @throws Exception
     */
    List<DepartmentDto> findDepartmentsByParentId(long pid) throws Exception;
}
