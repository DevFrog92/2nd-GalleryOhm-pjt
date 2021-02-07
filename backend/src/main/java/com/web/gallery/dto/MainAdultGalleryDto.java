package com.web.gallery.dto;

public class MainAdultGalleryDto {
    private int mainAdultGallery_galleryId;

    public MainAdultGalleryDto(int mainAdultGallery_galleryId) {
        this.mainAdultGallery_galleryId = mainAdultGallery_galleryId;
    }

    public int getMainAdultGallery_galleryId() {
        return mainAdultGallery_galleryId;
    }

    public void setMainAdultGallery_galleryId(int mainAdultGallery_galleryId) {
        this.mainAdultGallery_galleryId = mainAdultGallery_galleryId;
    }

    @Override
    public String toString() {
        return "MainAdultGalleryDto{" +
                "mainAdultGallery_galleryId=" + mainAdultGallery_galleryId +
                '}';
    }
}
