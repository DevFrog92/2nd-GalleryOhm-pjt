package com.web.gallery.dto;

public class FootPrintDto {
    private int footPrint_galleryId;        // 전시관 아이디
    private String footPrint_userId;        // 사용자 아이디

    public FootPrintDto() {
    }

    public FootPrintDto(int footPrint_galleryId, String footPrint_userId) {
        this.footPrint_galleryId = footPrint_galleryId;
        this.footPrint_userId = footPrint_userId;
    }

    public int getFootPrint_galleryId() {
        return footPrint_galleryId;
    }

    public void setFootPrint_galleryId(int footPrint_galleryId) {
        this.footPrint_galleryId = footPrint_galleryId;
    }

    public String getFootPrint_userId() {
        return footPrint_userId;
    }

    public void setFootPrint_userId(String footPrint_userId) {
        this.footPrint_userId = footPrint_userId;
    }

    @Override
    public String toString() {
        return "FootPrintDto{" +
                "footPrint_galleryId=" + footPrint_galleryId +
                ", footPrint_userId='" + footPrint_userId + '\'' +
                '}';
    }
}
