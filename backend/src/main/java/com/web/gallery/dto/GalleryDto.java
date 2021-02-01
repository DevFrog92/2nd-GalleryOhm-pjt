package com.web.gallery.dto;

public class GalleryDto {
    private int gallery_id;             // 전시관 아이디
    private String gallery_name;        // 전시관 제목
    private String gallery_desc;        // 전시관 설명
    private String gallery_artistId;    // 작가 아이디
    private String gallery_writeTime;   // 작성 시간
    private int gallery_mainWorkId;     // 메인작품 아이디
    private int gallery_footPrint;      // 전시관 발자국 수

    public GalleryDto() {
    }

    public GalleryDto(int gallery_id, String gallery_name, String gallery_desc, String gallery_artistId, String gallery_writeTime, int gallery_mainWorkId, int gallery_footPrint) {
        this.gallery_id = gallery_id;
        this.gallery_name = gallery_name;
        this.gallery_desc = gallery_desc;
        this.gallery_artistId = gallery_artistId;
        this.gallery_writeTime = gallery_writeTime;
        this.gallery_mainWorkId = gallery_mainWorkId;
        this.gallery_footPrint = gallery_footPrint;
    }

    public int getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(int gallery_id) {
        this.gallery_id = gallery_id;
    }

    public String getGallery_name() {
        return gallery_name;
    }

    public void setGallery_name(String gallery_name) {
        this.gallery_name = gallery_name;
    }

    public String getGallery_desc() {
        return gallery_desc;
    }

    public void setGallery_desc(String gallery_desc) {
        this.gallery_desc = gallery_desc;
    }

    public String getGallery_artistId() {
        return gallery_artistId;
    }

    public void setGallery_artistId(String gallery_artistId) {
        this.gallery_artistId = gallery_artistId;
    }

    public String getGallery_writeTime() {
        return gallery_writeTime;
    }

    public void setGallery_writeTime(String gallery_writeTime) {
        this.gallery_writeTime = gallery_writeTime;
    }

    public int getGallery_mainWorkId() {
        return gallery_mainWorkId;
    }

    public void setGallery_mainWorkId(int gallery_mainWorkId) {
        this.gallery_mainWorkId = gallery_mainWorkId;
    }

    public int getGallery_footPrint() {
        return gallery_footPrint;
    }

    public void setGallery_footPrint(int gallery_footPrint) {
        this.gallery_footPrint = gallery_footPrint;
    }

    @Override
    public String toString() {
        return "GalleryDto{" +
                "gallery_id=" + gallery_id +
                ", gallery_name='" + gallery_name + '\'' +
                ", gallery_desc='" + gallery_desc + '\'' +
                ", gallery_artistId='" + gallery_artistId + '\'' +
                ", gallery_writeTime='" + gallery_writeTime + '\'' +
                ", gallery_mainWorkId=" + gallery_mainWorkId +
                ", gallery_footPrint=" + gallery_footPrint +
                '}';
    }
}
