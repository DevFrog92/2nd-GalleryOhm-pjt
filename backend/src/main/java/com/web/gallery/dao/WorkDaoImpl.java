package com.web.gallery.dao;

import com.web.gallery.dto.CostDto;
import com.web.gallery.dto.HashTagDto;
import com.web.gallery.dto.ScrapDto;
import com.web.gallery.dto.WorkDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkDaoImpl {

    String ns = "com.web.gallery.dao.workDao.";

    @Autowired
    private SqlSession sqlSession;

    public void addWork(WorkDto work) {
        sqlSession.insert(ns + "addWork", work);
    }

    public WorkDto getWork(int work_id) {
        return sqlSession.selectOne(ns + "getWork", work_id);
    }

    public void modifyWork(WorkDto work) {
        sqlSession.update(ns + "modifyWork", work);
    }

    public void deleteWork(int work_id) {
        sqlSession.delete(ns + "deleteWork", work_id);
    }

    public List<WorkDto> getAllWork(int sort) {
        return sqlSession.selectList(ns + "getAllWork", sort);
    }

    public List<WorkDto> getMyWorks(String work_artistId) {
        return sqlSession.selectList(ns + "getMyWorks", work_artistId);
    }

    public int getMyWorksCount(String work_artistId) {
        return sqlSession.selectOne(ns + "getMyWorksCount", work_artistId);
    }

    public List<WorkDto> getFollowingWork(String follow_userId) {
        return sqlSession.selectList(ns + "getFollowingWork", follow_userId);
    }

    public void giveCostToWork(CostDto cost) {
        sqlSession.insert(ns + "giveCostToWork", cost);
        sqlSession.update(ns + "updateWorkCost", cost);
    }

    public int isCheckCost(CostDto cost) {
        return sqlSession.selectOne(ns + "isCheckCost", cost);
    }

    public void clearToWorkCost(CostDto cost) {
        sqlSession.delete(ns + "clearToWorkCost", cost);
        sqlSession.update(ns + "updateWorkCost", cost);
    }

    public void scrapWork(ScrapDto scrap) {
        sqlSession.insert(ns+"scrapWork", scrap);
    }

    public int isCheckScrap(ScrapDto scrap) {
        return sqlSession.selectOne(ns+"isCheckScrap", scrap);
    }

    public void clearToWorkScrap(ScrapDto scrap) {
        sqlSession.delete(ns+"clearToWorkScrap", scrap);
    }

    public List<WorkDto> getAllScrapWork(String scrap_userId) {
        return sqlSession.selectList(ns+"getAllScrapWork", scrap_userId);
    }

    public int isCheckHashTag(HashTagDto hashtag) {
        return sqlSession.selectOne(ns+"isCheckHashTag", hashtag);
    }

    public void addHashTag(HashTagDto hashtag) {
        sqlSession.insert(ns+"addHashTag",hashtag);
    }

    public int countWorkHashTag(int hashtag_workId) {
        return sqlSession.selectOne(ns+"countWorkHashTag", hashtag_workId);
    }

    public List<String> getWorkHashTag(int hashtag_workId) {
        return sqlSession.selectList(ns+"getWorkHashTag",hashtag_workId);
    }

    public List<WorkDto> searchByHashTag(List<String> hashtags) {
        return sqlSession.selectList(ns+"searchByHashTag", hashtags);
    }
}
