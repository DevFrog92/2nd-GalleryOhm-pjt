package com.web.gallery.dto;

public class WorkDto {
    private int work_id;                // 작품 아이디
    private String work_artistId;        // 작품 작가 아이디
    private String work_title;            // 작품 제목
    private String work_desc;            // 작품 설명
    private byte[] work_piece;            // 작품 그림
    private int work_cost;                // 작품 가치(좋아요 수)
    private int work_rating;            // 작품 연령
    private String work_uploadDate;        // 작품 업로드 시간
    private String work_tool;            // 작품 툴 설명

    private String[] hashtags;            // 작품 상세정보 리턴시 사용할 해시태그 배열

    public WorkDto() {
    }

    public WorkDto(int work_id, String work_artistId, String work_title, String work_desc, byte[] work_piece,
                   int work_cost, int work_rating, String work_uploadDate, String work_tool) {
        this.work_id = work_id;
        this.work_artistId = work_artistId;
        this.work_title = work_title;
        this.work_desc = work_desc;
        this.work_piece = work_piece;
        this.work_cost = work_cost;
        this.work_rating = work_rating;
        this.work_uploadDate = work_uploadDate;
        this.work_tool = work_tool;
    }

    public int getWork_id() {
        return work_id;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public String getWork_artistId() {
        return work_artistId;
    }

    public void setWork_artistId(String work_artistId) {
        this.work_artistId = work_artistId;
    }

    public String getWork_title() {
        return work_title;
    }

    public void setWork_title(String work_title) {
        this.work_title = work_title;
    }

    public String getWork_desc() {
        return work_desc;
    }

    public void setWork_desc(String work_desc) {
        this.work_desc = work_desc;
    }

    public byte[] getWork_piece() {
        return work_piece;
    }

    public void setWork_piece(byte[] work_piece) {
        this.work_piece = work_piece;
    }

    public int getWork_cost() {
        return work_cost;
    }

    public void setWork_cost(int work_cost) {
        this.work_cost = work_cost;
    }

    public int getWork_rating() {
        return work_rating;
    }

    public void setWork_rating(int work_rating) {
        this.work_rating = work_rating;
    }

    public String getWork_uploadDate() {
        return work_uploadDate;
    }

    public void setWork_uploadDate(String work_uploadDate) {
        this.work_uploadDate = work_uploadDate;
    }

    public String getWork_tool() {
        return work_tool;
    }

    public void setWork_tool(String work_tool) {
        this.work_tool = work_tool;
    }

    public String[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public String toString() {
        return "WorkDto [work_id=" + work_id + ", work_artistId=" + work_artistId + ", work_title=" + work_title
                + ", work_desc=" + work_desc + ", work_piece=" + work_piece + ", work_cost=" + work_cost
                + ", work_rating=" + work_rating + ", work_uploadDate=" + work_uploadDate + ", work_tool=" + work_tool + "]";
    }

}
