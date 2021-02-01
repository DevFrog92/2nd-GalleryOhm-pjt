package com.web.gallery.dto;

public class ScrapDto {
    private int scrap_id;                   // 스크랩 DB 아이디 (PK)
    private int scrap_workId;               // 스크랩한 작품 아이디
    private String scrap_userId;            // 스크랩한 사용자 아이디

    public ScrapDto() {
    }

    public ScrapDto(int scrap_id, int scrap_workId, String scrap_userId) {
        this.scrap_id = scrap_id;
        this.scrap_workId = scrap_workId;
        this.scrap_userId = scrap_userId;
    }

    public int getScrap_id() {
        return scrap_id;
    }

    public void setScrap_id(int scrap_id) {
        this.scrap_id = scrap_id;
    }

    public int getScrap_workId() {
        return scrap_workId;
    }

    public void setScrap_workId(int scrap_workId) {
        this.scrap_workId = scrap_workId;
    }

    public String getScrap_userId() {
        return scrap_userId;
    }

    public void setScrap_userId(String scrap_userId) {
        this.scrap_userId = scrap_userId;
    }

    @Override
    public String toString() {
        return "ScrapDto{" +
                "scrap_id=" + scrap_id +
                ", scrap_workId=" + scrap_workId +
                ", scrap_userId='" + scrap_userId + '\'' +
                '}';
    }
}
