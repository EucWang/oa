package cn.wxn.example.webapp.interceptor;

import cn.wxn.example.webapp.dto.PrivilegeDto;
import cn.wxn.example.webapp.entry.Privilege;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.service.PrivilegeService;
import cn.wxn.example.webapp.utils.SessionUserManager;
import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.service.UserService;
import cn.wxn.example.webapp.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangxn on 2016/10/13.
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Autowired
    private UserService userService;

    /**
     * 拦截前处理
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("method -> preHandle()");
        UserDto userById = SessionUserManager.getUserDtoFromSession(httpServletRequest.getSession(), userService);

        //首先用户拦截,获取用户信息是否正确
        if (userById != null) {
            httpServletRequest.setAttribute("username", userById.getName());

            return true;
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/loginUI");
        return false;
    }



    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
