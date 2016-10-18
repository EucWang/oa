package cn.wxn.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangxn on 2016/10/18.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
