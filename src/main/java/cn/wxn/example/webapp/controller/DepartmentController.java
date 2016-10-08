package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.dto.DepartmentDto;
import cn.wxn.example.webapp.exception.ParamFailException;
import cn.wxn.example.webapp.service.DepartmentService;
import cn.wxn.example.webapp.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wangxn on 2016/9/19.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    private Logger logger = Logger.getLogger(DepartmentController.class);

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @RequestMapping("/addUI")
    public ModelAndView addUI(ModelAndView modelAndView, String departmentid) throws Exception {
        modelAndView.setViewName("/department/addUI");


        //获取所有的department的集合,然后为其赋值其上级部门的对象
        List<DepartmentDto> departments = departmentService.findDepartments(-1L);
        if (departments != null && departments.size() > 0) {
            Collections.sort(departments, new Comparator<DepartmentDto>() {
                public int compare(DepartmentDto o1, DepartmentDto o2) {
                    Long o1id = o1.getParent().getId();
                    Long o2id = o2.getParent().getId();
                    if (o1id > o2id) {
                        return 1;
                    } else if (o1id == o2id) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });

            for (DepartmentDto departmentDto : departments) {
                if (departmentDto != null && departmentDto.getParent() != null && !"0".equals(departmentDto.getParent().getId())) {
                    for (DepartmentDto dd : departments) {
                        if (dd.getId().equals(departmentDto.getParent().getId())) {
                            departmentDto.setParent(dd);
                            break;
                        }
                    }
                }
            }
        }

        modelAndView.addObject("departments", departments);

        if (!StringUtils.isBlank(departmentid)) {
            modelAndView.addObject("departmentid", departmentid);
        }

        return modelAndView;
    }

    @RequestMapping("/editUI/{id}")
    public ModelAndView editUI(ModelAndView modelAndView, @PathVariable("id") String id, String departmentid) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new ParamFailException("DepartmentController->method(editUI) parameter id is empty");
        }
        Long aLong = Long.valueOf(id);
        if (aLong == null) {
            throw new ParamFailException("DepartmentController->method(editUI) parameter id convert to Long is null");
        }

        logger.info("###########################");
        logger.info("aLong ==== " + aLong);
        logger.info("###########################");
        DepartmentDto departmentById = departmentService.findDepartmentById(aLong);

        modelAndView.addObject("pid", departmentid);
        modelAndView.addObject("department", departmentById);
        modelAndView.setViewName("/department/editUI");


        List<DepartmentDto> departments = departmentService.findDepartments(-1L);
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

            if (!StringUtils.isBlank(departmentDto.getDepartmentid())) {
                modelAndView.addObject("pid", departmentDto.getDepartmentid());
            }

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
            if (!StringUtils.isBlank(departmentDto.getDepartmentid())) {
                modelAndView.addObject("pid", departmentDto.getDepartmentid());
            }
            modelAndView.setViewName("redirect:/department/list");
            return modelAndView;
        }
        return null;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") String id, ModelAndView modelAndView) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new ParamFailException("DepartmentController->method(editUI) parameter id is empty");
        }
        Long aLong = Long.valueOf(id);
        if (aLong == null) {
            throw new ParamFailException("DepartmentController->method(editUI) parameter id convert to Long is null");
        }

        boolean b = departmentService.deleteDepartment(aLong);
        if (b) {
            logger.info("删除id为" + id + "成功");
        } else {
            logger.info("删除id为" + id + "失败");
        }
        return b?"1":"0";
    }

    /**
     * @param pid          默认传值0,表示第一层的部门
     * @param modelAndView
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public ModelAndView list(String pid, ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("department/list");

        if (StringUtils.isBlank(pid)) {
            pid = "0";
        }
        Long pidLong = Long.valueOf(pid);
        if (pidLong == null || pidLong < 0) {
            pidLong = 0L;
        }

        List<DepartmentDto> departments = departmentService.findDepartments(pidLong);
        modelAndView.addObject("departments", departments);
        modelAndView.addObject("departmentid", pid);


        //TODO 进入下级部门,不能正确的显示返回上级部门
        String grandParentId = null;
        if (pidLong > 0) {
            DepartmentDto parentDepartment = departmentService.findDepartmentById(pidLong);
            if (parentDepartment != null) {
                DepartmentDto parent = parentDepartment.getParent();
                if (parent != null) {
                    Long id = parent.getId();
                    if (id != null) {
                        grandParentId = id.toString();
                    }
                }
            }
//            grandParentId = parentDepartment.getParent().getId().toString();
            if (grandParentId != null) {
                modelAndView.addObject("grandparentid", grandParentId);
            }
        }

        return modelAndView;
    }
}
