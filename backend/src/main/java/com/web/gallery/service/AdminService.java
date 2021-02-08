package com.web.gallery.service;

import com.web.gallery.dto.*;

import java.util.List;

public interface AdminService {

    // 전체 사용자 조회
    List<UserDto> getAllUser() throws Exception;

    // 작품 연령 등급 부여
    void giveRating(int work_id) throws Exception;

    // 작품 연령 등급 해제
    void clearRating(int work_id) throws Exception;

    // 추천 키워드(해시태그) 추가
    void addMainGalleryKeyword(List<String> keywordList) throws Exception;

    // 추천 키워드(해시태그) 삭제
    void deleteMainGalleryHashTag(String keyword_name) throws Exception;

    // 추천 키워드(해시태그) 목록 조회
    List<String> getMainGalleryHashTag() throws Exception;

    // 전체 해시태그 목록 조회
    List<HashTagDto> getAllHashTag() throws Exception;

    // 해시태그 삭제 - 해당 작품에서 태그된 것만 삭제
    void deleteHashTagFromWork(HashTagDto hashTagDto) throws Exception;

    // 해시태그 삭제 - 전체 해시태그에서 삭제
    void deleteHashTagFromTotal(String hashtag_name) throws Exception;

    // 메인관 작품 선정 (전시관) 목록
    List<MainGalleryDto> renewMainGallery() throws Exception;

    // 서브관 작품 선정 (작품) 목록
    List<SubGalleryDto> renewSubGallery() throws Exception;

    /**** 메인관 작품 선정 (전시관) ****/
    // 메인 갤러리 DB에 insert
    void renewMainGallery_algorithm() throws Exception;
    // 메인 갤러리 DB에 insert (19)
    void renewMainAdultGallery_algorithm() throws Exception;
    // 메인 갤러리 DB 데이터들 삭제
    void renewMainGallery_delete() throws Exception;
    // 메인 갤러리 DB에 데이터들 삭제 (19)
    void renewMainAdultGallery_delete() throws Exception;

    /**** 서브관 작품 선정 (작품) ****/
    void renewSubGallery_algorithm() throws Exception;

    void renewSubGallery_delete() throws Exception;

    // 메인 갤러리 키워드 전체 삭제
    void deleteMainGalleryKeyword() throws Exception;

    List<WorkDto> getAllWork();

    void addHashTagFromTotal(List<String> hashTagList);
}