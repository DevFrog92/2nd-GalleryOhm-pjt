package com.web.gallery.service;

import com.web.gallery.dto.MessageDto;

import java.util.List;

public interface MessageService {

    // 메세지 전송
    void sendMessage(MessageDto message) throws Exception;

    // 사용자가 받은 메세지 리턴
    List<MessageDto> getAllMyReceiveMessage(String user_id) throws Exception;

    // 사용자가 보낸 메세지 리턴
    List<MessageDto> getAllMySendMessage(String user_id) throws Exception;

    // 메세지 읽음 처리
    void checkMessage(int message_id) throws Exception;

    // 메세지의 세부정보 리턴
    MessageDto getMyMessage(int message_id) throws Exception;

    // 메세지 삭제
    void deleteMessage(int message_id) throws Exception;
}
