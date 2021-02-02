package com.web.gallery.dto;

public class SubGalleryDto {
    private int subGallery_workId;            // 작품 아이디

    public SubGalleryDto() {
    }

    public SubGalleryDto(int subGallery_workId) {
        this.subGallery_workId = subGallery_workId;
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
                "subGallery_workId=" + subGallery_workId +
                '}';
    }
}
