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

    public List<HashTagDto> getAllHashTag() throws SQLException {
        return sqlSession.selectList(ns + "getAllHashTag");
    }

    public void giveRating(int work_id) throws SQLException {
        sqlSession.update(ns + "giveRating", work_id);
    }

    public void clearRating(int work_id) {
        sqlSession.update(ns + "clearRating", work_id);
    }

    public void addMainGalleryKeyword(List<String> keywordList) {
        sqlSession.update(ns + "addMainGalleryKeyword", keywordList);
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

    public void deleteHashTagFromTotal(String hashtag_name) {
        sqlSession.delete(ns + "deleteHashTagFromTotal", hashtag_name);
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

    public void renewMainAdultGallery_algorithm() {
        sqlSession.insert(ns + "renewMainAdultGallery_algorithm");
    }

    public void renewMainAdultGallery_delete() {
        sqlSession.delete(ns + "renewMainAdultGallery_delete");
    }

    public void deleteMainGalleryKeyword() {
        sqlSession.delete(ns + "deleteMainGalleryKeyword");
    }

    public List<WorkDto> getAllWork() {
        return sqlSession.selectList(ns + "getAllWork");
    }

    public void addHashTagFromTotal(List<String> hashTagList) {
        sqlSession.insert(ns + "addHashTagFromTotal", hashTagList);
    }

    public void renewArtistExp() {
        sqlSession.update(ns + "renewArtistExp");
    }

    public void updateArtistExpUp(String artist_id) {
        sqlSession.update(ns + "updateArtistExpUp", artist_id);
    }

    public void updateArtistExpDown(String artist_id) {
        sqlSession.update(ns + "updateArtistExpDown", artist_id);
    }

    public List<ArtistDto> getAllArtist() {
        return sqlSession.selectList(ns + "getAllArtist");
    }
}