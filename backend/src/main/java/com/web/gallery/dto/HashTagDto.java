package com.web.gallery.dto;

public class HashTagDto {
    int hashtag_id;            // 해시태그 아이디, Auto Increment
    int hashtag_workId;        // 작품 아이디
    String hashtag_name;    // 해시태그 이름

    public HashTagDto() {
        super();
    }

    public HashTagDto(int hashtag_id, int hashtag_workId, String hashtag_name) {
        this.hashtag_id = hashtag_id;
        this.hashtag_workId = hashtag_workId;
        this.hashtag_name = hashtag_name;
    }

    public HashTagDto(int hashtag_workId, String hashtag_name) {
        this.hashtag_workId = hashtag_workId;
        this.hashtag_name = hashtag_name;
    }

    public int getHashtag_id() {
        return hashtag_id;
    }

    public void setHashtag_id(int hashtag_id) {
        this.hashtag_id = hashtag_id;
    }

    public int getHashtag_workId() {
        return hashtag_workId;
    }

    public void setHashtag_workId(int hashtag_workId) {
        this.hashtag_workId = hashtag_workId;
    }

    public String getHashtag_name() {
        return hashtag_name;
    }

    public void setHashtag_name(String hashtag_name) {
        this.hashtag_name = hashtag_name;
    }

    @Override
    public String toString() {
        return "HashtagDto{" +
                "hashtag_id=" + hashtag_id +
                ", hashtag_workId=" + hashtag_workId +
                ", hashtag_name='" + hashtag_name + '\'' +
                '}';
    }
}
