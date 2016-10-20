package cn.wxn.example.webapp.service.impl;

import cn.wxn.example.webapp.dao.ForumMapper;
import cn.wxn.example.webapp.dto.ForumDto;
import cn.wxn.example.webapp.entry.Forum;
import cn.wxn.example.webapp.exception.ParamFailException;
import cn.wxn.example.webapp.service.ForumService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxn on 2016/10/20.
 */
@Service("forumService")
@Transactional
public class ForumServiceImpl implements ForumService {

    private Logger logger = Logger.getLogger(ForumServiceImpl.class);

    @Autowired
    private ForumMapper forumMapper;

    public int addForum(ForumDto forumDto) throws Exception {

        Forum forum = new Forum();
        PropertyUtils.copyProperties(forum, forumDto);
        Integer maxPostion = forumMapper.findMaxPostion();
        if (maxPostion == null || maxPostion <= 0) {
            maxPostion = 0;
        }
        forum.setPosition(maxPostion + 1);

        return forumMapper.insertForum(forum);
    }

    public ForumDto findForumById(long id) throws Exception {
        Forum forumById = forumMapper.findForumById(id);
        ForumDto forumDto = new ForumDto();
        PropertyUtils.copyProperties(forumDto, forumById);
        return forumDto;
    }

    public ForumDto findForumByPosition(int position) throws Exception {
        Forum forumById = forumMapper.findForumByPosition(position);
        ForumDto forumDto = new ForumDto();
        PropertyUtils.copyProperties(forumDto, forumById);
        return forumDto;
    }

    public List<ForumDto> findForums() throws Exception {
        List<Forum> forums = forumMapper.findForums();
        List<ForumDto> forumDtos = null;
        if (forums != null && forums.size() > 0) {
            forumDtos = new ArrayList<ForumDto>();
            for (Forum forum : forums) {
                ForumDto forumDto = new ForumDto();
                PropertyUtils.copyProperties(forumDto, forum);
                forumDtos.add(forumDto);
            }
        }
        return forumDtos;
    }

    public int updateForum(ForumDto forumDto) throws Exception {
        Forum forum = new Forum();
        PropertyUtils.copyProperties(forum, forumDto);
        if (forum == null || forum.getId() == null) {
            throw new ParamFailException("ForumServiceImpl -> updateForum -> convert to forum ,but forum is null or forum's id is null");
        }

        return forumMapper.updateForum(forum);
    }

    public ForumDto deleteForum(long id) throws Exception {

        Forum forumById = forumMapper.findForumById(id);

        if (forumById == null) {
            logger.info("需要删除的版块不存在");
            return null;
        }

        int deleteColumn = forumMapper.deleteForum(id);
        if (deleteColumn < 0) {
            logger.info("删除失败");
            return null;
        }

        //将这个forum下的所有forum的position上移一位
        List<Forum> forumsAfterPosition = forumMapper.findForumsAfterPosition(forumById.getPosition());
        if (forumsAfterPosition != null && forumsAfterPosition.size() > 0) {
            for (Forum forum : forumsAfterPosition) {
                forum.setPosition(forum.getPosition() - 1);
                forumMapper.updateForum(forum);
            }
        }

        ForumDto forumDto = new ForumDto();
        PropertyUtils.copyProperties(forumDto, forumById);
        return forumDto;
    }

    public int moveForumPosition(ForumDto forumDto, boolean isUp) throws Exception {
        Forum forum = new Forum();
        PropertyUtils.copyProperties(forum, forumDto);


        Integer position = forumDto.getPosition();
        Forum brotherPositionForum = null;
        if (isUp) {
            //向上移动,position--
            if (position <= 1) {
                logger.info("不能继续往上移动了,已经到达最高位置了");
                return 0;
            }
            position--;
            brotherPositionForum = forumMapper.findForumByPosition(position);
            if (brotherPositionForum != null)
                brotherPositionForum.setPosition(brotherPositionForum.getPosition() + 1);
            forum.setPosition(forum.getPosition() - 1);
        } else {
            //向下移动, position ++
            Integer maxPostion = forumMapper.findMaxPostion();
            if (position >= maxPostion) {
                logger.info("不能继续往下移动了,已经到达最低位置了");
                return 0;
            }

            brotherPositionForum = forumMapper.findForumByPosition(position + 1);
            if (brotherPositionForum != null)
                brotherPositionForum.setPosition(brotherPositionForum.getPosition() - 1);
            forum.setPosition(forum.getPosition() + 1);
        }

        forumMapper.updateForum(forum);
        forumMapper.updateForum(brotherPositionForum);

        return 1;
    }
}
