package com.web.gallery.service;

import com.web.gallery.dto.GalleryDto;
import com.web.gallery.dto.WorkDto;

import java.util.HashMap;
import java.util.List;

public interface GalleryService {

    /**** 작가의 개인 전시관을 추가한다. ****/
    int addArtistGallery(String gallery_name, String gallery_desc, String gallery_artistId, int gallery_mainWorkId);

    /**** 작가의 개인 전시관에 작품들을 추가한다 ****/
    int addArtistGalleryDetail(List<Integer> workIdList);

    /**** 작가의 개인 전시관을 수정한다. ****/
    int modifyArtistGallery(int gallery_id, String gallery_name, String gallery_desc, String gallery_artistId, int gallery_mainWorkId);

    /**** 작가의 개인 전시관안의 작품들을 모두 삭제한다. ****/
    int deleteArtistGalleryDetail(int gallery_id);

    /**** 작가의 개인 전시관을 삭제한다. ****/
    int deleteArtistGallery(int gallery_id);

    /****  갤러리 아이디로 갤러리 정보를 조회한다. ****/
    GalleryDto getGallery(int gallery_id);

    /**** 메인 갤러리의 모든 전시관 목록들을 조회한다. ****/
    List<GalleryDto> getAllMainGallery();

    /**** 메인 갤러리의 전시관을 전시관 아이디로 삭제한다. ****/
    int deleteGalleryToMainGallery(int mainGallery_galleryId);

    /**** 서브 갤러리의 모든 작품 목록들을 조회한다. ****/
    List<WorkDto> getAllSubGallery();

    /**** 서브 갤러리의 작품을 작품 아이디로 삭제한다. ****/
    int deleteWorkToSubGallery(int subGallery_workId);

    /**** 전시관 속 작품들 목록 리스트를 반환한다. ****/
    List<WorkDto> getArtistGallery(int galleryDetail_galleryId);

    /**** 작가의 아이디로 작가의 전시관 리스트를 반환한다. ****/
    List<GalleryDto> getMyGallery(String gallery_artistId);

    /**** 전시관을 발자국(좋아요) 누른 사용자를 footprint DB에 저장한다. (한 사용자가 좋아요 두번이상 누르지 못하도록) ****/
    void giveFootPrintToGallery(int footPrint_galleryId, String footPrint_userId);

    // 성인 갤러리 작품 목록 반환
    List<WorkDto> getAdultGallery();

    // 성인 갤러리 홀수 작품 목록 반환
    List<WorkDto> getAdultGallery_odd();

    // 성인 갤러리 짝수 작품 목록 반환
    List<WorkDto> getAdultGallery_even();

    // 서브 갤러리 홀수 작품 목록 반환
    List<WorkDto> getAllSubGallery_odd();

    // 서브 갤러리 짝수 작품 목록 반환
    List<WorkDto> getAllSubGallery_even();

    // 연령 제한 작품 포함 여부 체크
    List<HashMap<String, Integer>> isAdultGallery(int gallery_id);

    // 갤러리 연령 등급 업데이트
    void updateGalleryForAdult(int gallery_id);

    // 갤러리 메인 아이디 받아오기
    int getGalleryId(int gallery_mainWorkId);

    /**** 메인 갤러리의 모든 전시관 목록들을 조회한다. (연령 제한 작품 포함) ****/
    List<GalleryDto> getAllMainAdultGallery();

    // 모든 갤러리 목록 반환
    List<GalleryDto> getAllGallery() throws Exception;
}
