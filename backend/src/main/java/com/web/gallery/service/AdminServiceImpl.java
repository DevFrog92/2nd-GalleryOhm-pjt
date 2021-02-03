package com.web.gallery.service;
import com.web.gallery.dao.AdminDaoImpl;
import com.web.gallery.dto.HashTagDto;
import com.web.gallery.dto.MainGalleryDto;
import com.web.gallery.dto.SubGalleryDto;
import com.web.gallery.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDaoImpl adminDao;

    @Override
    public List<UserDto> getAllUser() throws Exception {
        return adminDao.getAllUser();
    }

    @Override
    public void giveRating(int work_id) throws Exception {
        adminDao.giveRating(work_id);
    }

    @Override
    public void clearRating(int work_id) throws Exception {
        adminDao.clearRating(work_id);
    }

    @Override
    public void addMainGalleryHashTag(String keyword_name) throws Exception {
        adminDao.addMainGalleryHashTag(keyword_name);
    }

    @Override
    public void deleteMainGalleryHashTag(String keyword_name) throws Exception {
        adminDao.deleteMainGalleryHashTag(keyword_name);
    }

    @Override
    public List<String> getMainGalleryHashTag() throws Exception {
        return adminDao.getMainGalleryHashTag();
    }

    @Override
    public List<String> getAllHashTag() throws Exception {
        return adminDao.getAllHashTag();
    }

    @Override
    public void deleteHashTagFromWork(HashTagDto hashTagDto) throws Exception {
        adminDao.deleteHashTagFromWork(hashTagDto);
    }

    @Override
    public void deleteHashTagFromTotal(HashTagDto hashTagDto) throws Exception {
        adminDao.deleteHashTagFromTotal(hashTagDto);
    }

    @Override
    public List<MainGalleryDto> renewMainGallery() throws Exception {
        return adminDao.renewMainGallery();
    }

    @Override
    public List<SubGalleryDto> renewSubGallery() throws Exception {
        return adminDao.renewSubGallery();
    }

    @Override
    public void renewMainGallery_algorithm() throws Exception {
        adminDao.renewMainGallery_algorithm();
    }

    @Override
    public void renewMainGallery_delete() throws Exception {
        adminDao.renewMainGallery_delete();
    }

    @Override
    public void renewSubGallery_algorithm() throws Exception {
        adminDao.renewSubGallery_algorithm();
    }

    @Override
    public void renewSubGallery_delete() throws Exception {
        adminDao.renewSubGallery_delete();
    }
}