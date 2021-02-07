package com.web.gallery.service;

import com.web.gallery.dao.FollowDaoImpl;
import com.web.gallery.dto.FollowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowDaoImpl followDao;

    @Override
    public void follow(FollowDto follow) throws Exception {
        followDao.follow(follow);
    }

    @Override
    public void cancelFollow(FollowDto follow) throws Exception {
        followDao.cancelFollow(follow);
    }

    @Override
    public List<String> getAllMyFollowing(String user_id) throws Exception {
        return followDao.getAllMyFollowing(user_id);
    }

    @Override
    public List<String> getAllMyFollower(String user_id) throws Exception {
        return followDao.getAllMyFollower(user_id);
    }

    @Override
    public int isCheckFollow(FollowDto follow) throws Exception {
        return followDao.isCheckFollow(follow);
    }
}
