package com.web.gallery.service;

import com.web.gallery.dao.GalleryDaoImpl;
import com.web.gallery.dto.GalleryDto;
import com.web.gallery.dto.WorkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryDaoImpl galleryDao;

    /**** 작가의 개인 전시관을 추가한다. ****/
    @Override
    public int addArtistGallery(String gallery_name, String gallery_desc, String gallery_artistId, int gallery_mainWorkId) {
        System.out.println("개인 전시관 추가.");
        return galleryDao.addArtistGallery(gallery_name, gallery_desc, gallery_artistId, gallery_mainWorkId);
    }

    /**** 작가의 개인 전시관에 작품들을 추가한다. ****/
    @Override
    public int addArtistGalleryDetail(List<Integer> workIdList) {
        return galleryDao.addArtistGalleryDetail(workIdList);
    }

    /**** 작가의 개인 전시관을 수정한다. ****/
    @Override
    public int modifyArtistGallery(int gallery_id, String gallery_name, String gallery_desc, String gallery_artistId, int gallery_mainWorkId) {
        return galleryDao.modifyArtistGallery(gallery_id, gallery_name, gallery_desc, gallery_artistId, gallery_mainWorkId);
    }

    /**** 작가의 개인 전시관안의 작품들을 모두 삭제한다. ****/
    @Override
    public int deleteArtistGalleryDetail(int gallery_id) {
        return galleryDao.deleteArtistGalleryDetail(gallery_id);
    }

    /**** 작가의 개인 전시관을 삭제한다. ****/
    @Override
    public int deleteArtistGallery(int gallery_id) {
        return galleryDao.deleteArtistGallery(gallery_id);
    }

    /****  갤러리 아이디로 갤러리 정보를 조회한다. ****/
    @Override
    public GalleryDto getGallery(int gallery_id) {
        return galleryDao.getGallery(gallery_id);
    }

    /**** 메인 갤러리의 모든 전시관 목록들을 조회한다. ****/
    @Override
    public List<GalleryDto> getAllMainGallery() {
        return galleryDao.getAllMainGallery();
    }

    /**** 메인 갤러리의 전시관을 전시관 아이디로 삭제한다. ****/
    @Override
    public int deleteGalleryToMainGallery(int mainGallery_galleryId) {
        return galleryDao.deleteGalleryToMainGallery(mainGallery_galleryId);
    }

    /**** 서브 갤러리의 모든 작품 목록들을 조회한다. ****/
    @Override
    public List<WorkDto> getAllSubGallery() {
        return galleryDao.getAllSubGallery();
    }

    /**** 서브 갤러리의 작품을 작품 아이디로 삭제한다. ****/
    @Override
    public int deleteWorkToSubGallery(int subGallery_workId) {
        return galleryDao.deleteWorkToSubGallery(subGallery_workId);
    }

    /**** 전시관 속 작품들 목록 리스트를 반환한다. ****/
    @Override
    public List<WorkDto> getArtistGallery(int galleryDetail_galleryId) {
        return galleryDao.getArtistGallery(galleryDetail_galleryId);
    }

    /**** 작가의 아이디로 작가의 전시관 리스트를 반환한다. ****/
    @Override
    public List<GalleryDto> getMyGallery(String gallery_artistId) {
        return galleryDao.getMyGallery(gallery_artistId);
    }

    /**** 전시관을 발자국(좋아요) 누른 사용자를 footprint DB에 저장한다. (한 사용자가 좋아요 두번이상 누르지 못하도록) ****/
    @Override
    public void giveFootPrintToGallery(int footPrint_galleryId, String footPrint_userId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("footPrint_galleryId", footPrint_galleryId);
        map.put("footPrint_userId", footPrint_userId);

        galleryDao.giveFootPrintToGallery(map);
    }

    @Override
    public List<WorkDto> getAdultGallery() {
        return galleryDao.getAdultGallery();
    }

    @Override
    public List<WorkDto> getAdultGallery_odd() {
        return galleryDao.getAdultGallery_odd();
    }

    @Override
    public List<WorkDto> getAdultGallery_even() {
        return galleryDao.getAdultGallery_even();
    }

    @Override
    public List<WorkDto> getAllSubGallery_odd() {
        return galleryDao.getAllSubGallery_odd();
    }

    @Override
    public List<WorkDto> getAllSubGallery_even() {
        return galleryDao.getAllSubGallery_even();
    }

    @Override
    public List<HashMap<String, Integer>> isAdultGallery(int gallery_id) {
        return galleryDao.isAdultGallery(gallery_id);
    }

    @Override
    public void updateGalleryForAdult(int gallery_id) {
        galleryDao.updateGalleryForAdult(gallery_id);
    }

    @Override
    public int getGalleryId(int gallery_mainWorkId) {
        return galleryDao.getGalleryId(gallery_mainWorkId);
    }

    @Override
    public List<GalleryDto> getAllMainAdultGallery() {
        return galleryDao.getAllMainAdultGallery();
    }

    @Override
    public List<GalleryDto> getAllGallery() throws Exception {
        return galleryDao.getALlGallery();
    }

    @Override
    public List<GalleryDto> getAllAdultGallery() throws Exception {
        return galleryDao.getAllAdultGallery();
    }
}
