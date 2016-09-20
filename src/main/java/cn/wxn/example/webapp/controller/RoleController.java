package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.exception.ParamFailException;
import cn.wxn.example.webapp.service.RoleService;
import cn.wxn.example.webapp.service.impl.RoleServiceImpl;
import cn.wxn.example.webapp.utils.StringUtils;
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
@RequestMapping("/role")
public class RoleController {

    private Logger logger = Logger.getLogger(RoleController.class);

    @Autowired

    @Qualifier("roleService")
    private RoleService roleService;

    @RequestMapping("/addUI")
    public String addUI() throws Exception {
        return "role/addUI";
    }

    @RequestMapping("/editUI/{id}")
    public ModelAndView editUI(ModelAndView modelAndView, @PathVariable("id") String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new ParamFailException("RoleController->method(editUI)->parameter id is empty");
        }
        Long aLong = Long.valueOf(id);
        if (aLong == null || aLong < 0) {
            throw new ParamFailException("RoleController->method(editUI)->parameter id convert to Long is null or smaller than 0.");
        }
        RoleDto roleById = roleService.findRoleById(aLong);
        modelAndView.addObject("role", roleById);


        modelAndView.setViewName("/role/editUI");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(RoleDto roleDto) throws Exception {
        if (StringUtils.isBlank(roleDto.getName())) {
            throw new ParamFailException("RoleController->method(add)->parameter name is empty");
        }
        try {
            roleService.insertRole(roleDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/role/list";
    }

    @RequestMapping("/del/{id}")
    public boolean del(@PathVariable("id") String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new ParamFailException("RoleController->method(del)->parameter id is empty");
        }
        Long aLong = Long.valueOf(id);
        if (null == aLong || aLong < 0) {
            throw new ParamFailException("RoleController->method(del)->parameter id convert to Long fail.");
        }
        boolean b = roleService.deleteRole(aLong);
        if (b) {
            logger.info("delete Role success.");
        } else {
            logger.info("delete Role fail.");
        }
        return b;
//        return "redirect:/role/list";
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView) throws Exception {
        List<RoleDto> roles = null;
        try {
            roles = roleService.findRoles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (roles != null) {
            modelAndView.addObject("roles", roles);
        }
        modelAndView.setViewName("role/list");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public String edit(RoleDto roleDto) throws Exception {
        if (null == roleDto.getId()) {
            throw new ParamFailException("RoleController->method(edit)->parameter id is empty");
        }
        if (StringUtils.isBlank(roleDto.getName())) {
            throw new ParamFailException("RoleController->method(edit)->parameter name is empty");
        }
        boolean b = roleService.updateRole(roleDto);
        if (b) {
            logger.info("update Role success.");
        } else {
            logger.info("update Role fail.");
        }
        return "redirect:/role/list";
    }
}
