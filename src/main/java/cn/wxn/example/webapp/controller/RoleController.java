package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.dto.PrivilegeDto;
import cn.wxn.example.webapp.dto.RoleDto;
import cn.wxn.example.webapp.entry.Privilege;
import cn.wxn.example.webapp.exception.ParamFailException;
import cn.wxn.example.webapp.service.PrivilegeService;
import cn.wxn.example.webapp.service.RoleService;
import cn.wxn.example.webapp.service.impl.RoleServiceImpl;
import cn.wxn.example.webapp.utils.StringUtils;
import cn.wxn.example.webapp.utils.convertor.imple.GeneralBeanConvertor;
import cn.wxn.example.webapp.utils.convertor.imple.SimpleBeanConvertor;
import cn.wxn.example.webapp.vo.PrivilegeVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

    @Autowired
    private PrivilegeService privilegeService;

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

    @RequestMapping("/editPrivilegeUI/{roleid}")
    public ModelAndView editPrivilegeUI(@PathVariable("roleid") String roleid, ModelAndView modelAndView) throws Exception {
        List<PrivilegeDto> privileges = privilegeService.findPrivileges();
        List<PrivilegeVo> privilegeVos = convertPrivilegesDtoToVo(privileges);

        List<Long> rolePrivilegeIds = new ArrayList<Long>();
        if (!StringUtils.isNullOrEmpty(roleid)) {
            Long aLong = Long.valueOf(roleid);
            List<PrivilegeDto> rolePrivileges = privilegeService.findRolePrivileges(aLong);
            if (rolePrivileges != null && rolePrivileges.size() > 0) {
                for (PrivilegeDto privilegeDto : rolePrivileges) {
                    rolePrivilegeIds.add(privilegeDto.getId());
                }
            }
        } else {
            throw new ParamFailException("编辑岗位的权限时,传递的岗位的参数错误.");
        }

        for (Long id : rolePrivilegeIds) {
            for (PrivilegeVo privilegeVo:privilegeVos){
                if (id == privilegeVo.getId()) {
                    privilegeVo.setChecked(true);
                    break;
                }
            }
        }

        modelAndView.addObject("roleid", roleid);
        modelAndView.addObject("privileges", privilegeVos);
        modelAndView.setViewName("role/editPrivilegeUI");
        return modelAndView;
    }

    @RequestMapping("/editPrivilege")
    public String editPrivilege(String roleid, String privilegeIds, ModelAndView modelAndView) throws Exception {

        if (StringUtils.isNullOrEmpty(roleid)) {
            throw new ParamFailException("编辑岗位权限时,参数传递异常");
        }

        Long roleidLong = Long.valueOf(roleid);

        RoleDto roleById = roleService.findRoleById(roleidLong);
        if (roleById == null) {
            throw new ParamFailException("编辑岗位权限时,岗位信息信息错误");
        }

        if (StringUtils.isNullOrEmpty(privilegeIds)) {
            roleById.setPrivileges(null);
        } else {
            List<String> strings = StringUtils.split2List(privilegeIds, ",");
            List<Privilege> privileges = new ArrayList<Privilege>();
            if (strings != null && strings.size() > 0) {
                for (String id : strings) {
                    if (!StringUtils.isNullOrEmpty(id)) {
                        Long idLong = Long.valueOf(id);
                        Privilege privilege = new Privilege();
                        privilege.setId(idLong);
                        privileges.add(privilege);
                    }
                }
            }

            roleById.setPrivileges(privileges);
            privilegeService.updateRolePrivileges(roleById);
        }
        return "redirect:/role/list";
    }

    private PrivilegeVo convertPrivilegeDtoToVo(PrivilegeDto privilegeDto) throws Exception {
        if (privilegeDto == null) {
            return null;
        }

        PrivilegeVo privilegeVo = new PrivilegeVo();
        BeanUtils.copyProperties(privilegeDto, privilegeVo);
        GeneralBeanConvertor convertor = new GeneralBeanConvertor();
        convertor.convert(privilegeDto, privilegeVo);
        privilegeVo.setChecked(false);
        return privilegeVo;
    }

    private List<PrivilegeVo> convertPrivilegesDtoToVo(List<PrivilegeDto> privilegeDtos) throws Exception {
        if (privilegeDtos == null || privilegeDtos.size() == 0) {
            return null;
        }

        List<PrivilegeVo> privilegeVos = new ArrayList<PrivilegeVo>();
        for (PrivilegeDto privilegeDto : privilegeDtos) {
            privilegeVos.add(convertPrivilegeDtoToVo(privilegeDto));
        }
        return  privilegeVos;
    }
}
