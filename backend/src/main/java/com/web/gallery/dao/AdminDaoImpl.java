package com.web.gallery.dao;

import com.web.gallery.dto.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDaoImpl {

    String ns = "com.web.gallery.dao.adminDao.";

    @Autowired
    private SqlSession sqlSession;

    public List<UserDto> getAllUser() throws SQLException {
        return sqlSession.selectList(ns + "getAllUser");
    }

    public List<String> getAllHashTag() throws SQLException {
        return sqlSession.selectList(ns + "getAllHashTag");
    }

    public void giveRating(int work_id) throws SQLException {
        sqlSession.update(ns + "giveRating", work_id);
    }

    public void clearRating(int work_id) {
        sqlSession.update(ns + "clearRating", work_id);
    }

    public void addMainGalleryHashTag(String keyword_name) {
        sqlSession.update(ns + "addMainGalleryHashTag", keyword_name);
    }

    public void deleteMainGalleryHashTag(String keyword_name) {
        sqlSession.delete(ns + "deleteMainGalleryHashTag", keyword_name);
    }

    public List<String> getMainGalleryHashTag() {
        return sqlSession.selectList(ns + "getMainGalleryHashTag");
    }

    public void deleteHashTagFromWork(HashTagDto hashTagDto) {
        sqlSession.delete(ns + "deleteHashTagFromWork", hashTagDto);
    }

    public void deleteHashTagFromTotal(HashTagDto hashTagDto) {
        sqlSession.delete(ns + "deleteHashTagFromTotal", hashTagDto);
    }

    public List<MainGalleryDto> renewMainGallery() {
        return sqlSession.selectList(ns + "renewMainGallery");
    }

    public List<SubGalleryDto> renewSubGallery() {
        return sqlSession.selectList(ns + "renewSubGallery");
    }

    public void renewMainGallery_algorithm() {
        sqlSession.insert(ns + "renewMainGallery_algorithm");
    }

    public void renewMainGallery_delete() {
        sqlSession.delete(ns+"renewMainGallery_delete");
    }

    public void renewSubGallery_algorithm() {
        List<WorkDto> subGallery = sqlSession.selectList(ns + "renewSubGallery_algorithm");

        sqlSession.insert(ns + "renewSubGallery_insert", subGallery);
    }

    public void renewSubGallery_delete() {
        sqlSession.delete(ns + "renewSubGallery_delete");
    }
}