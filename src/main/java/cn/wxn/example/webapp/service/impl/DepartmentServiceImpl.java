package cn.wxn.example.webapp.service.impl;

import cn.wxn.example.webapp.dao.DepartmentMapper;
import cn.wxn.example.webapp.dto.DepartmentDto;
import cn.wxn.example.webapp.entry.Department;
import cn.wxn.example.webapp.service.DepartmentService;
import cn.wxn.example.webapp.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxn on 2016/9/12.
 */
@Service("DepartmentService")
//@WebService
public class DepartmentServiceImpl implements DepartmentService {

    private Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentMapper departmentMapper;

    public boolean insertDepartment(DepartmentDto departmentDto) throws Exception {
        if (departmentDto == null) {
            logger.debug("insertDepartment fail, parameter is null");
            return false;
        }
        Department department = convertDepartmentDtoToDepartment(departmentDto);
        if (department == null) {
            logger.debug("insertDepartment fail, convertDepartmentDtoToDepartment is fail, get null Department object");
            return false;
        }
        try {
            int i = departmentMapper.insertDepartment(department);
            if (i >= 0) {
                return true;
            }
        } catch (Exception ex) {
            logger.warn(ex);
            logger.debug("DepartmentServiceImpl:insertDepartment:fail.");
        }
        return false;
    }

    public boolean updateDepartment(DepartmentDto departmentDto) throws Exception {
        if (departmentDto == null) {
            logger.debug("updateDepartment fail, parameter is null");
            return false;
        }
        Department department = convertDepartmentDtoToDepartment(departmentDto);
        if (department == null) {
            logger.debug("updateDepartment fail, convertDepartmentDtoToDepartment is fail, get null Department object");
            return false;
        }
        try {
            int i = departmentMapper.updateDepartment(department);
            if (i > 0) {
                return true;
            }
        } catch (Exception ex) {
            logger.warn(ex);
            logger.debug("DepartmentServiceImpl:updateDepartment:fail.");
        }
        return false;
    }

    public boolean deleteDepartment(long id) throws Exception {
        if (id >= 0) {
            try {
                int i = departmentMapper.deleteDepartment(id);
                if (i > 0) {
                    return true;
                }
            } catch (Exception ex) {
                logger.warn(ex);
                logger.debug("DepartmentServiceImpl:updateDepartment:fail.");
            }
            return false;
        } else {
            logger.debug("DepartmentServiceImpl:updateDepartment:parameter is err. id=" + id);
            return false;
        }
    }

    public DepartmentDto findDepartmentById(long id) throws Exception {
        Department DepartmentById = departmentMapper.findDepartmentById(id);
        DepartmentDto DepartmentDto = convertDepartmentToDepartmentDto(DepartmentById);
        return DepartmentDto;
    }

    public List<DepartmentDto> findDepartments() throws Exception {
        List<Department> Departments = departmentMapper.findDepartments();
        return convertDepartmentsToDepartmentDtos(Departments);
    }


    private Department convertDepartmentDtoToDepartment(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }

        Department retVal = new Department();
        BeanUtils.copyProperties(departmentDto, retVal);

        if (departmentDto.getParent() != null){
            Department parent = new Department();
            BeanUtils.copyProperties(departmentDto.getParent(), parent);
            retVal.setParent(parent);
        }

        return retVal;
    }

    private DepartmentDto convertDepartmentToDepartmentDto(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto retVal = new DepartmentDto();
        BeanUtils.copyProperties(department, retVal);

        if (department.getParent() != null){
            DepartmentDto parent = new DepartmentDto();
            BeanUtils.copyProperties(department.getParent(), parent);
            retVal.setParent(parent);
        }
        return retVal;
    }

    private List<DepartmentDto> convertDepartmentsToDepartmentDtos(List<Department> departments) {
        if (departments == null) {
            return null;
        }

        List<DepartmentDto> departmentDtos = new ArrayList<DepartmentDto>();
        if (departments != null && departments.size() > 0) {
            for (Department Department : departments) {
                departmentDtos.add(convertDepartmentToDepartmentDto(Department));
            }
        }
        return departmentDtos;
    }

}
