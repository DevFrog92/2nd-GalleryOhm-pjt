package com.web.gallery.service;

import com.web.gallery.dao.LoginDaoImpl;
import com.web.gallery.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDaoImpl loginDao;

    @Override
    public UserDto login(UserDto user) throws Exception {
        if (user == null)
            return null;
        return loginDao.login(user);
    }

    @Override
    public String findId(UserDto user) throws Exception {
        if (user.getUser_name() == null || user.getUser_email() == null) {
            throw new Exception();
        }
        return loginDao.findId(user);
    }

    @Override
    public String findPassword(UserDto user) throws Exception {
        if (user.getUser_id() == null || user.getUser_email() == null || user.getUser_tel() == null) {
            throw new Exception();
        }
        return loginDao.findPassword(user);
    }
}
