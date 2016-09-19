package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.dto.DepartmentDto;
import cn.wxn.example.webapp.exception.ParamFailException;
import cn.wxn.example.webapp.service.DepartmentService;
import cn.wxn.example.webapp.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangxn on 2016/9/19.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    private Logger logger = Logger.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/addUI")
    public ModelAndView addUI(ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("/department/addUI");

        List<DepartmentDto> departments = departmentService.findDepartments();
        modelAndView.addObject("departments", departments);
        return modelAndView;
    }

    @RequestMapping("/editUI/{id}")
    public ModelAndView editUI(ModelAndView modelAndView, @PathVariable("id") String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new ParamFailException("DepartmentController->method(editUI) parameter id is empty");
        }
        Long aLong = Long.valueOf(id);
        if (aLong == null) {
            throw new ParamFailException("DepartmentController->method(editUI) parameter id convert to Long is null");
        }

        DepartmentDto departmentById = departmentService.findDepartmentById(aLong);

        modelAndView.addObject("department", departmentById);
        modelAndView.setViewName("/department/editUI");


        List<DepartmentDto> departments = departmentService.findDepartments();
        modelAndView.addObject("departments", departments);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, DepartmentDto departmentDto) throws Exception {
        if (departmentDto == null) {
            throw new ParamFailException("DepartmentController->method(add) parameter departmentDto is null");
        }

        boolean b = departmentService.insertDepartment(departmentDto);
        if (b) {
            modelAndView.setViewName("redirect:/department/list");
            return modelAndView;
        }
        return null;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(ModelAndView modelAndView, DepartmentDto departmentDto) throws Exception {

        if (departmentDto == null) {
            throw new ParamFailException("DepartmentController->method(add) parameter departmentDto is null");
        }

        boolean b = departmentService.updateDepartment(departmentDto);
        if (b) {
            modelAndView.setViewName("redirect:/department/list");
            return modelAndView;
        }
        return null;
    }

    @RequestMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id, ModelAndView modelAndView) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new ParamFailException("DepartmentController->method(editUI) parameter id is empty");
        }
        Long aLong = Long.valueOf(id);
        if (aLong == null) {
            throw new ParamFailException("DepartmentController->method(editUI) parameter id convert to Long is null");
        }

        boolean b = departmentService.deleteDepartment(aLong);
        return b;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("department/list");

        List<DepartmentDto> departments = departmentService.findDepartments();
        modelAndView.addObject("departments", departments);
        return modelAndView;
    }
}
