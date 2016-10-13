package cn.wxn.example.webapp.interceptor;

import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.service.UserService;
import cn.wxn.example.webapp.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangxn on 2016/10/13.
 */
public class LoginInterceptor implements HandlerInterceptor {

    private final String ADMIN_SESSION = "userToken";

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
        String attribute = (String) httpServletRequest.getSession().getAttribute(ADMIN_SESSION);
        UserDto userById = getUserDtoFromSession(attribute);

        if (userById != null) {
            httpServletRequest.setAttribute("username", userById.getName());
            return true;
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/loginUI");
        return false;
    }

    private UserDto getUserDtoFromSession(String attribute) throws Exception {
        UserDto userById = null;
        if (!StringUtils.isNullOrEmpty(attribute)) {
            logger.info(attribute.toString());

            //格式: oa_1_1476341388663;
            List<String> strings = StringUtils.split2List(attribute, "_");
            if (strings != null && strings.size() == 3 && !StringUtils.isNullOrEmpty(strings.get(1))) {
                Long userIdL = Long.valueOf(strings.get(1));
                userById = userService.findUserById(userIdL);


            }
        }
        return userById;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
