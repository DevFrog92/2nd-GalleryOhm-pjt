package com.web.gallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.gallery.dao.UserDaoImpl;
import com.web.gallery.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDaoImpl UserDao;

	@Override
	public void joinUser(UserDto user) {
		UserDao.joinUser(user);
	}

	@Override
	public void leaveUser(String user_id) {
		UserDao.leaveUser(user_id);
	}

	@Override
	public void modifyUser(UserDto user) {
		UserDao.modifyUser(user);
	}

	@Override
	public void modifyUserAbout(UserDto user) {
		UserDao.modifyUserAbout(user);
	}

	@Override
	public int checkPassword(String user_id, String input_password) {
		return UserDao.checkPassword(user_id, input_password);
	}

	@Override
	public void modifyUserNickName(String user_id, String input_nickName) {
		UserDao.modifyUserNickName(user_id, input_nickName);
	}

	@Override
	public String makeUserNickName() {
		return UserDao.makeUserNickName();
	}

	@Override
	public int checkId(String user_id) {
		return UserDao.checkId(user_id);
	}

	@Override
	public UserDto getUserInfo(String user_id) {
		return UserDao.getUserInfo(user_id);
	}

	@Override
	public byte[] getUserProfile(String user_id) throws Exception {
		return UserDao.getUserProfile(user_id);
	}

}