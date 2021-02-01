package com.web.gallery.dao;

import com.web.gallery.dto.MessageDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl {

    String ns = "com.web.gallery.dao.messageDao.";

    @Autowired
    private SqlSession sqlSession;

    public void sendMessage(MessageDto message) {
        sqlSession.insert(ns + "sendMessage", message);
    }

    public List<MessageDto> getAllMyReceiveMessage(String user_id) {
        return sqlSession.selectList(ns+"getAllMyReceiveMessage", user_id);
    }

    public List<MessageDto> getAllMySendMessage(String user_id) {
        return sqlSession.selectList(ns+"getAllMySendMessage", user_id);
    }

    public void checkMessage(int message_id) {
        sqlSession.update(ns+"checkMessage", message_id);
    }

    public MessageDto getMyMessage(int message_id) {
        return sqlSession.selectOne(ns+"getMyMessage", message_id);
    }

    public void deleteMessage(int message_id) {
        sqlSession.delete(ns+"deleteMessage", message_id);
    }
}
