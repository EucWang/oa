package cn.wxn.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangxn on 2016/10/8.
 */
@Controller
public class Index {

    @RequestMapping("/nav-head")
    public String navHead() {
        return "nav-head";
    }

    @RequestMapping("/nav-menu")
    public String navMenu() {
        return "nav-menu";
    }
}
