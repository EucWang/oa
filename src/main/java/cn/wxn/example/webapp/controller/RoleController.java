package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RoleServiceImpl roleService;

    @RequestMapping("/addUI")
    public String addUI() {
        return "role/addUI";
    }

    @RequestMapping("/editUI")
    public String editUI() {
        return "/role/editUI";
    }

    @RequestMapping("/add")
    public String add(String name, String description) {
        RoleDto role = new RoleDto();
        role.setName(name);
        role.setDescription(description);
        try {
            roleService.insertRole(role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:role/list";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable String id) {
        return null;
    }

    @RequestMapping("/allRoles")
    public ModelAndView list(ModelAndView modelAndView) {
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
    public String edit(RoleDto roleDto) {
        return null;
    }
}
