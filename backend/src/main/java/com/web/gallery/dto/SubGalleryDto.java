package com.web.gallery.dto;

public class SubGalleryDto {
    private String subGallery_artistId;       // 작가 아이디
    private int subGallery_workId;            // 작품 아이디

    public SubGalleryDto() {
    }

    public SubGalleryDto(String subGallery_artistId, int subGallery_workId) {
        this.subGallery_artistId = subGallery_artistId;
        this.subGallery_workId = subGallery_workId;
    }

    public String getSubGallery_artistId() {
        return subGallery_artistId;
    }

    public void setSubGallery_artistId(String subGallery_artistId) {
        this.subGallery_artistId = subGallery_artistId;
    }

    public int getSubGallery_workId() {
        return subGallery_workId;
    }

    public void setSubGallery_workId(int subGallery_workId) {
        this.subGallery_workId = subGallery_workId;
    }

    @Override
    public String toString() {
        return "SubGalleryDto{" +
                "subGallery_artistId=" + subGallery_artistId +
                ", subGallery_workId=" + subGallery_workId +
                '}';
    }
}
