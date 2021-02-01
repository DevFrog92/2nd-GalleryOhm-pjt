package com.web.gallery.dto;

public class NickNameDto {
    int nickname_id;            // 닉네임 아이디, Auto Increment
    String nickname_pre;        // 닉네임 형용사
    String nickname_post;       // 닉네임 명사

    public NickNameDto(){
        super();
    }

    public NickNameDto(int nickname_id, String nickname_pre, String nickname_post) {
        this.nickname_id = nickname_id;
        this.nickname_pre = nickname_pre;
        this.nickname_post = nickname_post;
    }

    public int getNickname_id() {
        return nickname_id;
    }

    public void setNickname_id(int nickname_id) {
        this.nickname_id = nickname_id;
    }

    public String getNickname_pre() {
        return nickname_pre;
    }

    public void setNickname_pre(String nickname_pre) {
        this.nickname_pre = nickname_pre;
    }

    public String getNickname_post() {
        return nickname_post;
    }

    public void setNickname_post(String nickname_post) {
        this.nickname_post = nickname_post;
    }

    @Override
    public String toString() {
        return "NicknameDto{" +
                "nickname_id=" + nickname_id +
                ", nickname_pre='" + nickname_pre + '\'' +
                ", nickname_post='" + nickname_post + '\'' +
                '}';
    }
}
