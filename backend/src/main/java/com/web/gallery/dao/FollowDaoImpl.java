package com.web.gallery.dao;

import com.web.gallery.dto.FollowDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowDaoImpl {

    String ns = "com.web.gallery.dao.followDao.";

    @Autowired
    private SqlSession sqlSession;

    public void follow(FollowDto follow) {
        sqlSession.insert(ns + "follow", follow);
    }

    public void cancelFollow(FollowDto follow) {
        sqlSession.update(ns + "cancelFollow", follow);
    }

    public List<String> getAllMyFollowing(String user_id) {
        return sqlSession.selectList(ns + "getAllMyFollowing", user_id);
    }

    public List<String> getAllMyFollower(String user_id) {
        return sqlSession.selectList(ns + "getAllMyFollower", user_id);
    }
}
