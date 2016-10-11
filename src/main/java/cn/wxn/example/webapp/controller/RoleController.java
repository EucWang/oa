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
import java.util.*;

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
        //获取所有权限
        List<PrivilegeVo> privilegeVos = privilegeService.findFullRootPrivileges();

        //获取岗位上赋予的权限的id列表
        List<Long> rolePrivilegeIds = new ArrayList<Long>();
        if (!StringUtils.isNullOrEmpty(roleid)) {
            Long aLong = Long.valueOf(roleid);
            List<PrivilegeDto> rolePrivileges = privilegeService.findRolePrivileges(aLong); //获取岗位上赋予的权限
            if (rolePrivileges != null && rolePrivileges.size() > 0) {
                for (PrivilegeDto privilegeDto : rolePrivileges) {
                    rolePrivilegeIds.add(privilegeDto.getId());
                }
            }
        } else {
            throw new ParamFailException("编辑岗位的权限时,传递的岗位的参数错误.");
        }

        Long[] roleprivilegesIdsL = new Long[rolePrivilegeIds.size()];
        rolePrivilegeIds.toArray(roleprivilegesIdsL);


        //为已经选择的权限设置选中属性,以便界面可以用这个属性回显数据
        setPrivilegeVosOneLevelChecked(privilegeVos, roleprivilegesIdsL);

        modelAndView.addObject("privileges", privilegeVos);
        modelAndView.addObject("roleid", roleid);
        modelAndView.setViewName("role/editPrivilegeUI");
        return modelAndView;
    }

    private void setPrivilegeVosOneLevelChecked(List<PrivilegeVo> privilegeVos, Long[] ids) {
        for (PrivilegeVo privilegeVo : privilegeVos) {
            if (privilegeVo.getChildren() != null && privilegeVo.getChildren().size() > 0) {
                setPrivilegeVosOneLevelChecked(privilegeVo.getChildren(), ids);
            }

            if (Arrays.binarySearch(ids, privilegeVo.getId()) > 0) {
                privilegeVo.setChecked(true);
            }
        }
    }

    /**
     * 对权限列表进行排序
     * @param privilegeVos1 权限列表集合
     * @param totalParentCount 总的上层的父权限个数
     * @param level 上一层的父权限个数
     */
    private void sortPrvilegeVos(LinkedList<PrivilegeVo> privilegeVos1, int totalParentCount, int level) {
        int parentCountTmp = 0;
        for (int i = totalParentCount; i < privilegeVos1.size(); i++) {
            PrivilegeVo privilegeVo = privilegeVos1.get(i);
            if (privilegeVo.getParent() == null && level == 0) {
                privilegeVos1.addFirst(privilegeVos1.remove(i));
                parentCountTmp++;
            } else {
                if (privilegeVo.getLevel() == level) {
                    int i1 = privilegeVos1.indexOf(privilegeVo.getParent()); //获取直系父权限的索引位置
                    privilegeVos1.add(i1 + 1, privilegeVos1.remove(i));
                    parentCountTmp++;
                } else {
                    continue;
                }
            }
        }
        totalParentCount += parentCountTmp;
        if (totalParentCount < privilegeVos1.size()) {
            sortPrvilegeVos(privilegeVos1, totalParentCount, level + 1);
        }else {
            return;
        }
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


}
