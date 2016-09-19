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

    List<DepartmentDto> findDepartments() throws Exception;
}
