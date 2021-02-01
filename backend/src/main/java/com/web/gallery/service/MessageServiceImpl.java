package com.web.gallery.service;

import com.web.gallery.dao.MessageDaoImpl;
import com.web.gallery.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDaoImpl messageDao;

    @Override
    public void sendMessage(MessageDto message) throws Exception {
        messageDao.sendMessage(message);
    }

    @Override
    public List<MessageDto> getAllMyReceiveMessage(String user_id) throws Exception {
        return messageDao.getAllMyReceiveMessage(user_id);
    }

    @Override
    public List<MessageDto> getAllMySendMessage(String user_id) throws Exception {
        return messageDao.getAllMySendMessage(user_id);
    }

    @Override
    public void checkMessage(int message_id) throws Exception {
        messageDao.checkMessage(message_id);
    }

    @Override
    public MessageDto getMyMessage(int message_id) throws Exception {
        return messageDao.getMyMessage(message_id);
    }

    @Override
    public void deleteMessage(int message_id) throws Exception {
        messageDao.deleteMessage(message_id);
    }


}
