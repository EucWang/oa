package cn.wxn.example.webapp.service;

import cn.wxn.example.webapp.dto.ForumDto;
import cn.wxn.example.webapp.entry.Forum;

import java.util.List;

/**
 * Created by wangxn on 2016/10/20.
 */
public interface ForumService {

    int addForum(ForumDto forumDto)throws  Exception;

    ForumDto findForumById(long id) throws Exception;

    ForumDto findForumByPosition(int position) throws Exception;

    List<ForumDto> findForums() throws Exception;

    int updateForum(ForumDto forumDto) throws Exception;

    ForumDto deleteForum(long id) throws Exception;

    int moveForumPosition(ForumDto forumDto, boolean isUp) throws Exception;
}
