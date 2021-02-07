package com.web.gallery.service;

import com.web.gallery.dto.FollowDto;

import java.util.List;

public interface FollowService {

    // 팔로우 하기
    void follow(FollowDto follow) throws Exception;

    // 팔로우 취소
    void cancelFollow(FollowDto follow) throws Exception;

    // 팔로잉 목록 조회
    List<String> getAllMyFollowing(String user_id) throws Exception;

    // 팔로워 목록 조회
    List<String> getAllMyFollower(String user_id) throws Exception;

    // 팔로우 여부 조회
    int isCheckFollow(FollowDto follow) throws Exception;
}
