package com.web.gallery.dto;

public class MainGalleryDto {
    private int mainGallery_galleryId;          // 갤러리 아이디
    private String mainGallery_artistId;        // 작가 아이디

    public MainGalleryDto() {
    }

    public MainGalleryDto(int mainGallery_galleryId, String mainGallery_artistId) {
        this.mainGallery_galleryId = mainGallery_galleryId;
        this.mainGallery_artistId = mainGallery_artistId;
    }

    public int getMainGallery_galleryId() {
        return mainGallery_galleryId;
    }

    public void setMainGallery_galleryId(int mainGallery_galleryId) {
        this.mainGallery_galleryId = mainGallery_galleryId;
    }

    public String getMainGallery_artistId() {
        return mainGallery_artistId;
    }

    public void setMainGallery_artistId(String mainGallery_artistId) {
        this.mainGallery_artistId = mainGallery_artistId;
    }

    @Override
    public String toString() {
        return "MainGalleryDto{" +
                "mainGallery_galleryId=" + mainGallery_galleryId +
                ", mainGallery_artistId=" + mainGallery_artistId +
                '}';
    }
}
