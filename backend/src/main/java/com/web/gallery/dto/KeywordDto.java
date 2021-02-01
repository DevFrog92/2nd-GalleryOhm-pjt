package com.web.gallery.dto;

public class KeywordDto {
    private int keyword_id;         // 키워드 아이디
    private String keyword_name;    // 키워드 이름

    public KeywordDto() {
    }

    public KeywordDto(int keyword_id, String keyword_name) {
        this.keyword_id = keyword_id;
        this.keyword_name = keyword_name;
    }

    public int getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(int keyword_id) {
        this.keyword_id = keyword_id;
    }

    public String getKeyword_name() {
        return keyword_name;
    }

    public void setKeyword_name(String keyword_name) {
        this.keyword_name = keyword_name;
    }

    @Override
    public String toString() {
        return "KeywordDto{" +
                "keyword_id=" + keyword_id +
                ", keyword_name='" + keyword_name + '\'' +
                '}';
    }
}
