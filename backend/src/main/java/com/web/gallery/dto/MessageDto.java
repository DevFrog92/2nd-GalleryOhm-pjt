package com.web.gallery.dto;

public class MessageDto {
    int message_id;                   // 메세지 아이디, Auto Increment
    String message_receiverId;        // 받는사람 아이디
    String message_senderId;          // 보내는사람 아이디
    String message_title;             // 메세지 제목
    String message_content;           // 메세지 내용
    String message_sendDate;          // 보낸 날짜, Default now()
    int message_isCheck;              // 확인 체크, Default 1

    public MessageDto(){
        super();
    }

    public MessageDto(int message_id, String message_receiverId, String message_senderId, String message_title, String message_content, String message_sendDate, int message_isCheck) {
        this.message_id = message_id;
        this.message_receiverId = message_receiverId;
        this.message_senderId = message_senderId;
        this.message_title = message_title;
        this.message_content = message_content;
        this.message_sendDate = message_sendDate;
        this.message_isCheck = message_isCheck;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_receiverId() {
        return message_receiverId;
    }

    public void setMessage_receiverId(String message_receiverId) {
        this.message_receiverId = message_receiverId;
    }

    public String getMessage_senderId() {
        return message_senderId;
    }

    public void setMessage_senderId(String message_senderId) {
        this.message_senderId = message_senderId;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getMessage_sendDate() {
        return message_sendDate;
    }

    public void setMessage_sendDate(String message_sendDate) {
        this.message_sendDate = message_sendDate;
    }

    public int getMessage_isCheck() {
        return message_isCheck;
    }

    public void setMessage_isCheck(int message_isCheck) {
        this.message_isCheck = message_isCheck;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "message_id=" + message_id +
                ", message_receiverId='" + message_receiverId + '\'' +
                ", message_senderId='" + message_senderId + '\'' +
                ", message_title='" + message_title + '\'' +
                ", message_content='" + message_content + '\'' +
                ", message_sendDate='" + message_sendDate + '\'' +
                ", message_isCheck=" + message_isCheck +
                '}';
    }
}
