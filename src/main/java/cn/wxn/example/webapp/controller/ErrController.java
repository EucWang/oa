package cn.wxn.example.webapp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by wangxn on 2016/10/14.
 */
@RequestMapping("/err")
@Controller
public class ErrController {

    private Logger logger = Logger.getLogger(ErrController.class);

    @RequestMapping("/msg")
    public ModelAndView msg(HttpSession session, ModelAndView modelAndView) {
        String msg = (String) session.getAttribute("msg");
        session.setAttribute("msg", "");
        logger.info("ErrController -> method msg() -> String msg = " + msg);
        modelAndView.addObject("info", msg);
        modelAndView.setViewName("err/msg");
        return modelAndView;
    }
}
