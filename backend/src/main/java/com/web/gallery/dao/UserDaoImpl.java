package com.web.gallery.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.web.gallery.dto.UserDto;

@Repository
public class UserDaoImpl {

	String ns = "com.web.gallery.dao.userDao.";

	@Autowired
	private SqlSession sqlSession;

	public UserDto login(UserDto user) throws SQLException {
		return sqlSession.selectOne(ns + "login", user);
	}

	public void joinUser(UserDto user) {
		sqlSession.insert(ns + "joinUser", user);
	}

	public void modifyUser(UserDto user) {
		sqlSession.update(ns + "modifyUser", user);
	}

	public int checkId(UserDto user) throws SQLException {
		return sqlSession.selectOne(ns + "checkId", user);
	}

	public void leaveUser(String user_id) {
		sqlSession.delete(ns + "leaveUser", user_id);
	}

	public int checkPassword(String user_id, String input_password) {
		return sqlSession.selectOne(ns + "checkPassword", new UserDto(user_id, input_password));
	}

	// 닉네임 직접 수정
	public void modifyUserNickName(String user_id, String input_nickName) {
		UserDto user = new UserDto();
		user.setUser_id(user_id);
		user.setUser_nickName(input_nickName);
		sqlSession.update(ns + "modifyUserNickName", user);
	}

	// 새 랜덤 닉네임 생성
	public String makeUserNickName() {
		return sqlSession.selectOne(ns + "makeUserNickName");
	}

	public UserDto getUserInfo(String user_id) {
		return sqlSession.selectOne(ns + "getUserInfo", user_id);
	}

	public byte[] getUserProfile(String user_id) {
		return sqlSession.selectOne(ns + "getUserProfile", user_id);
	}

	public void modifyUserAbout(UserDto user) {
		sqlSession.update(ns + "modifyUserAbout", user);
	}

	public int checkId(String user_id) {
		return sqlSession.selectOne(ns + "checkId", user_id);
	}
}
