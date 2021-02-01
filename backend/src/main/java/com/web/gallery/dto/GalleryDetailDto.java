package com.web.gallery.dto;

public class GalleryDetailDto {
    private int galleryDetail_galleryId;        // 전시관 아이디
    private int galleryDetail_workId;           // 작품 아이디

    public GalleryDetailDto() {
    }

    public GalleryDetailDto(int galleryDetail_galleryId, int galleryDetail_workId) {
        this.galleryDetail_galleryId = galleryDetail_galleryId;
        this.galleryDetail_workId = galleryDetail_workId;
    }

    public int getGalleryDetail_galleryId() {
        return galleryDetail_galleryId;
    }

    public void setGalleryDetail_galleryId(int galleryDetail_galleryId) {
        this.galleryDetail_galleryId = galleryDetail_galleryId;
    }

    public int getGalleryDetail_workId() {
        return galleryDetail_workId;
    }

    public void setGalleryDetail_workId(int galleryDetail_workId) {
        this.galleryDetail_workId = galleryDetail_workId;
    }

    @Override
    public String toString() {
        return "GalleryDetailDto{" +
                "galleryDetail_galleryId=" + galleryDetail_galleryId +
                ", galleryDetail_workId=" + galleryDetail_workId +
                '}';
    }
}
