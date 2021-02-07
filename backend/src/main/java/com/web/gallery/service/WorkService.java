package com.web.gallery.service;

import com.web.gallery.dto.CostDto;
import com.web.gallery.dto.HashTagDto;
import com.web.gallery.dto.ScrapDto;
import com.web.gallery.dto.WorkDto;

import java.util.List;

public interface WorkService {
    
    // 작품 추가
    int addWork(WorkDto work) throws Exception;

    // 작품의 세부정보 리턴
    WorkDto getWork(int work_id) throws Exception;

    // 작품 수정
    void modifyWork(WorkDto work) throws Exception;

    // 작품 삭제
    void deleteWork(int work_id) throws Exception;

    // 작품 전체 받아오기(정렬 기준)
    List<WorkDto> getAllWork(int sort) throws Exception;

    // 나의 작품 목록 받아오기
    List<WorkDto> getMyWorks(String work_artistId) throws Exception;

    // 내 작품 수 받아오기
    int getMyWorksCount(String work_artistId) throws Exception;

    // 팔로우한 작가의 작품 목록 받아오기
    List<WorkDto> getFollowingWork(String follow_userId) throws Exception;

    // 작품 좋아요(cost 부여)
    void giveCostToWork(CostDto cost) throws Exception;

    // 작품 좋아요 여부 확인
    int isCheckCost(CostDto cost) throws Exception;

    // 작품 좋아요(cost 부여) 취소
    void clearToWorkCost(CostDto cost) throws Exception;

    // 작품 즐겨찾기
    void scrapWork(ScrapDto scrap) throws Exception;

    // 작품 즐겨찾기 확인
    int isCheckScarp(ScrapDto scrap) throws Exception;

    // 작품 즐겨찾기 취소
    void clearToWorkScrap(ScrapDto scrap) throws Exception;

    // 즐겨찾기한 작품 목록 반환
    List<WorkDto> getAllScrapWork(String scrap_userId) throws Exception;

    // 작품에 해당 해시태그가 있는 여부 확인
    int isCheckHashTag(HashTagDto hashtag) throws Exception;

    // 작품에 해시태그 저장
    void addHashTag(HashTagDto hashtag) throws Exception;

    // 작품의 해시태그 개수 반환
    int countWorkHashTag(int hashtag_workId) throws Exception;

    // 작품의 해시태그 목록 반환
    List<String> getWorkHashTag(int hashtag_workId) throws Exception;

    // 해시태그로 작품 검색
    List<WorkDto> searchByHashTag(List<String> hashtags) throws Exception;

    // 해시태그 목록 삭제
    void deleteHashTag(int work_id) throws Exception;
}
