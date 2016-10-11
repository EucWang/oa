package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.entry.Privilege;
import cn.wxn.example.webapp.service.PrivilegeService;
import cn.wxn.example.webapp.vo.PrivilegeVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by wangxn on 2016/10/8.
 */
@Controller
@RequestMapping("/nav")
public class HeadMenuController {

    private Logger logger = Logger.getLogger(HeadMenuController.class);

    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping("/navhead")
    public String navHead() {
        return "nav/navhead";
    }

    @RequestMapping("/navmenu")
    public ModelAndView navMenu(ModelAndView modelAndView) throws Exception {

        List<PrivilegeVo> fullPrivileges = privilegeService.findFullRootPrivileges();

        removeNoMenu(fullPrivileges);

        modelAndView.addObject("privileges", fullPrivileges);
        modelAndView.setViewName("nav/navmenu");

        return modelAndView;
    }

    private void removeNoMenu(List<PrivilegeVo> fullPrivileges) {
        Iterator<PrivilegeVo> iterator = fullPrivileges.iterator();
        while (iterator.hasNext()) {
            PrivilegeVo next = iterator.next();
            if (next.getIsMenu() == 0) {
                iterator.remove();
            } else {
                if (next.getChildren() != null && next.getChildren().size() > 0) {
                    removeNoMenu(next.getChildren());
                }
            }
        }
    }
}
