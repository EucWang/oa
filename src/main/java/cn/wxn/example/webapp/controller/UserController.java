package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.dto.DepartmentDto;
import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.entry.Department;
import cn.wxn.example.webapp.exception.ParamFailException;
import cn.wxn.example.webapp.service.DepartmentService;
import cn.wxn.example.webapp.service.RoleService;
import cn.wxn.example.webapp.service.UserService;
import cn.wxn.example.webapp.utils.StringUtils;
import cn.wxn.example.webapp.vo.UserVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangxn on 2016/9/12.
 * 岗位
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @RequestMapping("/addUI")
    public ModelAndView addUI(ModelAndView modelAndView) throws Exception {

        List<DepartmentDto> departments = departmentService.findDepartmentsByParentId(-1L);
        modelAndView.addObject("departments", departments);
//        List<RoleDto> roles = roleService.findRoles();
//        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("user/addUI");

        return modelAndView;
    }

    @RequestMapping("/editUI/{id}")
    public ModelAndView editUI(ModelAndView modelAndView, @PathVariable("id") String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new ParamFailException("userController->method(editUI)->parameter id is empty");
        }
        Long aLong = Long.valueOf(id);
        if (aLong == null || aLong < 0) {
            throw new ParamFailException("userController->method(editUI)->parameter id convert to Long is null or smaller than 0.");
        }
        UserDto userById = userService.findUserById(aLong);
        modelAndView.addObject("user", userById);

        List<DepartmentDto> departments = departmentService.findDepartmentsByParentId(-1L);
        modelAndView.addObject("departments", departments);

        modelAndView.setViewName("/user/editUI");
        return modelAndView;
    }



    @RequestMapping("/add")
    public String add(UserVo userVo) throws Exception {
        if (StringUtils.isBlank(userVo.getName())) {
            throw new ParamFailException("userController->method(add)->parameter name is empty");
        }

        UserDto userDto = new UserDto();

        userDto.setName(userVo.getName());
        userDto.setNickname(userVo.getNickname());
        userDto.setGender(userVo.getGender());
        userDto.setPassword(userVo.getPassword());
        userDto.setPhoneNumber(userVo.getPhoneNum());
        userDto.setEmail(userVo.getEmail());
        userDto.setDescription(userVo.getDescription());
        userDto.setBirthday(userVo.getBirthday());
        userDto.setDepartment(userVo.convertToEmptyDepartment());

        try {
            userService.insertUser(userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/list";
    }


    @RequestMapping("/del/{id}")
    public boolean del(@PathVariable("id") String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new ParamFailException("userController->method(del)->parameter id is empty");
        }
        Long aLong = Long.valueOf(id);
        if (null == aLong || aLong < 0) {
            throw new ParamFailException("userController->method(del)->parameter id convert to Long fail.");
        }
        boolean b = userService.deleteUser(aLong);
        if (b) {
            logger.info("delete user success.");
        } else {
            logger.info("delete user fail.");
        }
        return b;
//        return "redirect:/user/list";
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView) throws Exception {
        List<UserDto> users = null;
        try {
            users = userService.findUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users != null) {
            modelAndView.addObject("users", users);
        }
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public String edit(UserVo userVo) throws Exception {

        if (StringUtils.isBlank(userVo.getName()) || StringUtils.isBlank(userVo.getId())) {
            throw new ParamFailException("userController->method(add)->parameter name is empty");
        }

        Long aLong1 = Long.valueOf(userVo.getId());
        if (aLong1 == null || aLong1 < 0) {
            throw new ParamFailException("userController->method(add)->parameter id is null or litter than zero.");
        }

        UserDto userDto = new UserDto();
        userDto.setId(aLong1);
        userDto.setName(userVo.getName());
        userDto.setNickname(userVo.getNickname());
        userDto.setGender(userVo.getGender());
        userDto.setPassword(userVo.getPassword());
        userDto.setPhoneNumber(userVo.getPhoneNum());
        userDto.setEmail(userVo.getEmail());
        userDto.setDescription(userVo.getDescription());
        userDto.setBirthday(userVo.getBirthday());
        userDto.setDepartment(userVo.convertToEmptyDepartment());

        if (null == userDto.getId()) {
            throw new ParamFailException("userController->method(edit)->parameter id is empty");
        }
        if (StringUtils.isBlank(userDto.getName())) {
            throw new ParamFailException("userController->method(edit)->parameter name is empty");
        }
        boolean b = userService.updateUser(userDto);
        if (b) {
            logger.info("update user success.");
        } else {
            logger.info("update user fail.");
        }
        return "redirect:/user/list";
    }

}
