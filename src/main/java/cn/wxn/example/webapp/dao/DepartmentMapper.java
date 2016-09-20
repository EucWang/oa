package cn.wxn.example.webapp.dao;

import cn.wxn.example.webapp.entry.Department;

import java.util.List;

/**
 * Created by wangxn on 2016/9/8.
 */
public interface DepartmentMapper extends ICaptchaMapper {

    int insertDepartment(Department department);

    int updateDepartment(Department department);

    int deleteDepartment(long id);

    Department findDepartmentById(long id);

    List<Department> findDepartments(long pid);
}
