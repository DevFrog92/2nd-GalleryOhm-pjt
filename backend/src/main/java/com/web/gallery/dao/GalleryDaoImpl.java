package com.web.gallery.dao;

import com.web.gallery.dto.GalleryDto;
import com.web.gallery.dto.WorkDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GalleryDaoImpl {
    String ns = "com.web.gallery.dao.galleryDao.";

    @Autowired
    private SqlSession sqlSession;

    public int addArtistGallery(String gallery_name, String gallery_desc, String gallery_artistId, int gallery_mainWorkId) {
        GalleryDto gallery = new GalleryDto();
        gallery.setGallery_name(gallery_name);
        gallery.setGallery_desc(gallery_desc);
        gallery.setGallery_artistId(gallery_artistId);
        gallery.setGallery_mainWorkId(gallery_mainWorkId);
        return sqlSession.insert(ns+"addArtistGallery", gallery);
    }

    public int addArtistGalleryDetail(List<Integer> workIdList) {
        return sqlSession.insert(ns+"addArtistGalleryDetail", workIdList);
    }

    public int modifyArtistGallery(int gallery_id, String gallery_name, String gallery_desc, String gallery_artistId, int gallery_mainWorkId) {
        GalleryDto gallery = new GalleryDto();
        gallery.setGallery_id(gallery_id);
        gallery.setGallery_name(gallery_name);
        gallery.setGallery_desc(gallery_desc);
        gallery.setGallery_artistId(gallery_artistId);
        gallery.setGallery_mainWorkId(gallery_mainWorkId);
        return sqlSession.update(ns+"modifyArtistGallery", gallery);
    }

    public int deleteArtistGallery(int gallery_id) {
        return sqlSession.delete(ns+"deleteArtistGallery", gallery_id);
    }

    public GalleryDto getGallery(int gallery_id) {
        return sqlSession.selectOne(ns+"getGallery", gallery_id);
    }

    public List<GalleryDto> getAllMainGallery() {
        return sqlSession.selectList(ns+"getAllMainGallery");
    }

    public int deleteArtistGalleryDetail(int gallery_id) {
        return sqlSession.delete(ns+"deleteArtistGalleryDetail", gallery_id);
    }

    public int deleteGalleryToMainGallery(int mainGallery_galleryId) {
        return sqlSession.delete(ns+"deleteGalleryToMainGallery", mainGallery_galleryId);
    }

    public List<WorkDto> getAllSubGallery() {
        return sqlSession.selectList(ns+"getAllSubGallery");
    }

    public int deleteWorkToSubGallery(int subGallery_workId) {
        return sqlSession.delete(ns+"deleteWorkToSubGallery",subGallery_workId);
    }

    public List<WorkDto> getArtistGallery(int galleryDetail_galleryId) {
        return sqlSession.selectList(ns+"getArtistGallery", galleryDetail_galleryId);
    }

    public List<GalleryDto> getMyGallery(String gallery_artistId) {
        return sqlSession.selectList(ns+"getMyGallery",gallery_artistId);
    }

    public void giveFootPrintToGallery(HashMap<String, Object> map) {
        sqlSession.insert(ns+"giveFootPrintToGallery",map);
    }


    public List<WorkDto> getAdultGallery() {
        return sqlSession.selectList(ns+"getAdultGallery");
    }
}
