package cn.wxn.example.webapp.utils;

import cn.wxn.example.webapp.dto.PrivilegeDto;
import cn.wxn.example.webapp.dto.UserDto;
import cn.wxn.example.webapp.entry.Privilege;
import cn.wxn.example.webapp.entry.Role;
import cn.wxn.example.webapp.service.PrivilegeService;
import cn.wxn.example.webapp.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangxn on 2016/10/14.
 */
public class SessionUserManager {

    private static Logger logger = Logger.getLogger(SessionUserManager.class);

    /**
     * 保存用户session的key值字符串
     */
    public static final String ADMIN_SESSION = "userToken";

    /**
     *  从Session中获取用户的信息
     * @param session
     * @param userService
     * @return
     * @throws Exception
     */
    public static UserDto getUserDtoFromSession(HttpSession session, UserService userService) throws Exception {
        UserDto userById = null;

        String attribute = (String) session.getAttribute(ADMIN_SESSION);
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

    public static Set<PrivilegeDto> getUserPrivilegeDtos(UserDto userDto, PrivilegeService privilegeService) throws  Exception{
        Set<PrivilegeDto> privilegeDtos = null;
        if (userDto.getRoles() != null && userDto.getRoles().size() > 0) {
            privilegeDtos = new HashSet<PrivilegeDto>();
            for (Role role : userDto.getRoles()) {
                List<PrivilegeDto> privileges = privilegeService.findRolePrivileges(role.getId());
                privilegeDtos.addAll(privileges);
            }
        }
        return privilegeDtos;
    }
}
