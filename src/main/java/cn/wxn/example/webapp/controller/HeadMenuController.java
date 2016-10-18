package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.dto.PrivilegeDto;
import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.service.PrivilegeService;
import cn.wxn.example.webapp.service.UserService;
import cn.wxn.example.webapp.utils.SessionUserManager;
import cn.wxn.example.webapp.vo.PrivilegeVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by wangxn on 2016/10/8.
 */
@Controller
@RequestMapping("/nav")
public class HeadMenuController {

    private Logger logger = Logger.getLogger(HeadMenuController.class);

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private UserService userService;

    @RequestMapping("/navhead")
    public ModelAndView navHead(HttpSession httpSession, ModelAndView modelAndView, HttpServletRequest request)throws Exception {

        UserDto userDtoFromSession = SessionUserManager.getUserDtoFromSession(httpSession, userService);
        if (userDtoFromSession == null) {
            httpSession.setAttribute("msg", "请先登录再操作!");
            modelAndView.setViewName("redirect:/err/msg");
            return modelAndView;
        }

        modelAndView.addObject("username", userDtoFromSession.getName());
        modelAndView.setViewName("nav/navhead");
        return modelAndView;
    }

    @RequestMapping("/navmenu")
    public ModelAndView navMenu(ModelAndView modelAndView, HttpSession httpSession) throws Exception {

        UserDto userById = SessionUserManager.getUserDtoFromSession(httpSession, userService);

        if (userById == null) {
            return null;
        }

        Set<PrivilegeDto> privilegeDtos = SessionUserManager.getUserPrivilegeDtos(userById, privilegeService);

        if (privilegeDtos == null || privilegeDtos.size() == 0) {
            return null;
        }

        Set<PrivilegeVo> privilegeVos = PrivilegeDto.convertPrivilegesDtoToVo(privilegeDtos);
        removeNoMenu(privilegeVos);
        List<PrivilegeVo> prvilegeVosList = new ArrayList();
        prvilegeVosList.addAll(privilegeVos);
        Collections.sort(prvilegeVosList, new Comparator<PrivilegeVo>() {
            public int compare(PrivilegeVo o1, PrivilegeVo o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        modelAndView.addObject("privileges", prvilegeVosList);
        modelAndView.setViewName("nav/navmenu");

        return modelAndView;
    }

    private void removeNoMenu(Set<PrivilegeVo> fullPrivileges) {
        Iterator<PrivilegeVo> iterator = fullPrivileges.iterator();
        while (iterator.hasNext()) {
            PrivilegeVo next = iterator.next();
            if (next.getIsMenu() <= 0) {
                iterator.remove();
            }
        }
    }
}
