package com.web.gallery.dto;

public class MainGalleryDto {
    private int mainGallery_galleryId;          // 갤러리 아이디

    public MainGalleryDto() {
    }

    public MainGalleryDto(int mainGallery_galleryId) {
        this.mainGallery_galleryId = mainGallery_galleryId;
    }

    public int getMainGallery_galleryId() {
        return mainGallery_galleryId;
    }

    public void setMainGallery_galleryId(int mainGallery_galleryId) {
        this.mainGallery_galleryId = mainGallery_galleryId;
    }

    @Override
    public String toString() {
        return "MainGalleryDto{" +
                "mainGallery_galleryId=" + mainGallery_galleryId +
                '}';
    }
}
