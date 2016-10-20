package cn.wxn.example.webapp.controller;

import cn.wxn.example.webapp.dto.ForumDto;
import cn.wxn.example.webapp.exception.ParamFailException;
import cn.wxn.example.webapp.service.ForumService;
import cn.wxn.example.webapp.utils.StringUtils;
import cn.wxn.example.webapp.vo.ForumVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wangxn on 2016/10/19.
 * 论坛管理 -> 版块管理
 */
@Controller
@RequestMapping("/forum")
public class ForumController {

    private Logger logger = Logger.getLogger(ForumController.class);

    @Autowired
    private ForumService forumService;

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView) throws Exception {

        List<ForumDto> forums = forumService.findForums();

        Collections.sort(forums, new Comparator<ForumDto>() {
            public int compare(ForumDto o1, ForumDto o2) {
                return o1.getPosition().compareTo(o2.getPosition());
            }
        });

        modelAndView.addObject("forums", forums);
        modelAndView.setViewName("forum/list");
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, ForumVo forumVo) throws Exception {

        ForumDto forumDto = new ForumDto();
        PropertyUtils.copyProperties(forumDto, forumVo);

        int i = forumService.addForum(forumDto);
        if (i <= 0) {
            logger.debug("ForumController -> add() fail!!!!");
        }

        modelAndView.setViewName("redirect:/forum/list");
        return modelAndView;
    }

    @RequestMapping("/addUI")
    public ModelAndView addUI(ModelAndView modelAndView) {
        logger.info("ForumController -> addUI()");
        modelAndView.setViewName("forum/addUI");
        return modelAndView;
    }

    @RequestMapping("/editUI")
    public ModelAndView editUI(ModelAndView modelAndView, String forumid) throws Exception {
        if (StringUtils.isNullOrEmpty(forumid)) {
            logger.debug("ForumController -> editUI receive null parameter.");
            throw new ParamFailException("ForumController -> editUI 接受参数错误");
        }
        Long forumIdL = Long.valueOf(forumid);
        if (forumid == null) {
            throw new ParamFailException("ForumController -> editUI 接受参数格式错误");
        }

        ForumDto forumById = forumService.findForumById(forumIdL);
        modelAndView.addObject("forum", forumById);
        modelAndView.setViewName("forum/editUI");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(ModelAndView modelAndView, ForumDto forum) throws Exception {

        if (forum == null) {
            logger.debug("ForumController -> edit receive null parameter.");
            throw new ParamFailException("ForumController -> edit 接受参数错误");
        }

        int i = forumService.updateForum(forum);
        if (i <= 0) {
            logger.error("更新失败");
            throw new ParamFailException("ForumController -> edit 更新失败");
        }

        modelAndView.setViewName("redirect:/forum/list");
        return modelAndView;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int delete(String forumId) {
        if (StringUtils.isNullOrEmpty(forumId)) {
            return 0;
        }

        Long forumIdL = Long.valueOf(forumId);
        if (forumId == null) {
            return 0;
        }
        ForumDto forumDto = null;
        try {
            forumDto = forumService.deleteForum(forumIdL);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
            return 0;
        }

        if (forumDto == null) {
            return 0;
        }

        return 1;
    }

    @RequestMapping("/movePosition")
    @ResponseBody
    public Integer movePosition(ModelAndView modelAndView, String forumId, Boolean isUp) {
        if (StringUtils.isNullOrEmpty(forumId)) {
            return 0;
        }

        Long forumIdL = Long.valueOf(forumId);
        if (forumId == null) {
            return 0;
        }

        ForumDto forumById = null;
        try {
            forumById = forumService.findForumById(forumIdL);

            if (forumById == null) {
                return 0;
            }

            int i = forumService.moveForumPosition(forumById, isUp);
            if (i <= 0) {
                return 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.debug(ex.getMessage());
            return 0;
        }
        return 1;
    }
}
