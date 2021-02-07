package com.web.gallery.service;

import com.web.gallery.dao.WorkDaoImpl;
import com.web.gallery.dto.CostDto;
import com.web.gallery.dto.HashTagDto;
import com.web.gallery.dto.ScrapDto;
import com.web.gallery.dto.WorkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService{

    @Autowired
    private WorkDaoImpl workDao;

    @Override
    public int addWork(WorkDto work) throws Exception {
        return workDao.addWork(work);
    }

    @Override
    public WorkDto getWork(int work_id) throws Exception {
        return workDao.getWork(work_id);
    }

    @Override
    public void modifyWork(WorkDto work) throws Exception {
        workDao.modifyWork(work);
    }

    @Override
    public void deleteWork(int work_id) throws Exception {
        workDao.deleteWork(work_id);
    }

    @Override
    public List<WorkDto> getAllWork(int sort) throws Exception {
        return workDao.getAllWork(sort);
    }

    @Override
    public List<WorkDto> getMyWorks(String work_artistId) throws Exception {
        return workDao.getMyWorks(work_artistId);
    }

    @Override
    public int getMyWorksCount(String work_artistId) throws Exception {
        return workDao.getMyWorksCount(work_artistId);
    }

    @Override
    public List<WorkDto> getFollowingWork(String follow_userId) throws Exception {
        return workDao.getFollowingWork(follow_userId);
    }

    @Override
    public void giveCostToWork(CostDto cost) throws Exception {
        workDao.giveCostToWork(cost);
    }

    @Override
    public int isCheckCost(CostDto cost) throws Exception {
        return workDao.isCheckCost(cost);
    }

    @Override
    public void clearToWorkCost(CostDto cost) throws Exception {
        workDao.clearToWorkCost(cost);
    }

    @Override
    public void scrapWork(ScrapDto scrap) throws Exception {
        workDao.scrapWork(scrap);
    }

    @Override
    public int isCheckScarp(ScrapDto scrap) throws Exception {
        return workDao.isCheckScrap(scrap);
    }

    @Override
    public void clearToWorkScrap(ScrapDto scrap) throws Exception {
        workDao.clearToWorkScrap(scrap);
    }

    @Override
    public List<WorkDto> getAllScrapWork(String scrap_userId) throws Exception {
        return workDao.getAllScrapWork(scrap_userId);
    }

    @Override
    public int isCheckHashTag(HashTagDto hashtag) throws Exception {
        return workDao.isCheckHashTag(hashtag);
    }

    @Override
    public void addHashTag(HashTagDto hashtag) throws Exception {
        workDao.addHashTag(hashtag);
    }

    @Override
    public int countWorkHashTag(int hashtag_workId) throws Exception {
        return workDao.countWorkHashTag(hashtag_workId);
    }

    @Override
    public List<String> getWorkHashTag(int hashtag_workId) throws Exception {
        return workDao.getWorkHashTag(hashtag_workId);
    }

    @Override
    public List<WorkDto> searchByHashTag(List<String> hashtags) throws Exception {
        return workDao.searchByHashTag(hashtags);
    }
}
