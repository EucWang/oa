package cn.wxn.example.webapp.dao;

import cn.wxn.example.webapp.entry.Forum;

import java.util.List;

/**
 * Created by wangxn on 2016/10/19.
 */
public interface ForumMapper extends ICaptchaMapper{

    int insertForum(Forum forum);

    int updateForum(Forum forum);

    int deleteForum(long id);

    Forum findForumById(long id);

    Forum findForumByPosition(long position);

    List<Forum> findForums();

    Integer findMaxPostion();

    List<Forum> findForumsAfterPosition(int position);
}
