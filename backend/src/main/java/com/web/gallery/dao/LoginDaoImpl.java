package com.web.gallery.dao;

import com.web.gallery.dto.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class LoginDaoImpl {

    String ns = "com.web.gallery.dao.loginDao.";

    @Autowired
    private SqlSession sqlSession;

    public UserDto login(UserDto user) throws SQLException {
        return sqlSession.selectOne(ns + "login", user);
    }

    public String findId(UserDto user) {
        return sqlSession.selectOne(ns + "findId", user);
    }

    public String findPassword(UserDto user) {
        return sqlSession.selectOne(ns + "findPassword", user);
    }
}
