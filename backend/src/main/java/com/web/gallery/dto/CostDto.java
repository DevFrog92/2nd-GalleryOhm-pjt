package com.web.gallery.dto;

public class CostDto {
    private int cost_workId;            // 작품 아이디
    private String cost_userId;         // 사용자 아이디

    public CostDto() {
    }

    public CostDto(int cost_workId, String cost_userId) {
        this.cost_workId = cost_workId;
        this.cost_userId = cost_userId;
    }

    public int getCost_workId() {
        return cost_workId;
    }

    public void setCost_workId(int cost_workId) {
        this.cost_workId = cost_workId;
    }

    public String getCost_userId() {
        return cost_userId;
    }

    public void setCost_userId(String cost_userId) {
        this.cost_userId = cost_userId;
    }

    @Override
    public String toString() {
        return "CostDto{" +
                "cost_workId=" + cost_workId +
                ", cost_userId=" + cost_userId +
                '}';
    }
}
