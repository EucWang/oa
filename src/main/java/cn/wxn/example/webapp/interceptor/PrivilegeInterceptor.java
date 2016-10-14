package cn.wxn.example.webapp.interceptor;

import cn.wxn.example.webapp.dto.PrivilegeDto;
import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.service.PrivilegeService;
import cn.wxn.example.webapp.service.UserService;
import cn.wxn.example.webapp.utils.SessionUserManager;
import cn.wxn.example.webapp.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by wangxn on 2016/10/14.
 */
public class PrivilegeInterceptor implements HandlerInterceptor {


    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeService privilegeService;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean hasPrivilege = false;
        UserDto userById = SessionUserManager.getUserDtoFromSession(httpServletRequest.getSession(), userService);

        //首先用户拦截,获取用户信息是否正确
        if (userById != null) {
            httpServletRequest.setAttribute("username", userById.getName());

            //然后获取用户的权限,判断是否有访问这个界面的权限
            Set<PrivilegeDto> privilegeDtos = SessionUserManager.getUserPrivilegeDtos(userById, privilegeService);

            String servletPath = httpServletRequest.getServletPath();
            logger.info("method -> preHandle() get ServletPath is ::: " + servletPath);

            if (privilegeDtos != null && privilegeDtos.size() > 0) {
                for (PrivilegeDto privilegeDto : privilegeDtos) {
                    if (!StringUtils.isNullOrEmpty(privilegeDto.getUrl())) {
                        if (privilegeDto.getUrl().equals(servletPath)) {
                            hasPrivilege = true;
                            break;
                        }
                    }
                }
            }

        }

        if (!hasPrivilege) {
            httpServletRequest.getSession().setAttribute("msg", "你没有权限访问这个页面!");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/err/msg");
        }
        return hasPrivilege;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
