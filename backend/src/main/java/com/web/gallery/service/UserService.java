package com.web.gallery.service;

import com.web.gallery.dto.UserDto;

public interface UserService {

	// 회원 가입
	void joinUser(UserDto user) throws Exception;

	// 회원 탈퇴
	void leaveUser(String user_id) throws Exception;

	// 회원 정보 수정
	void modifyUser(UserDto user) throws Exception;

	// 회원 소개글 수정
	void modifyUserAbout(UserDto user) throws Exception;

	// 비밀번호 일치 확인
	int checkPassword(String user_id, String input_password) throws Exception;

	// 사용자 닉네임 수정
	void modifyUserNickName(String user_id, String input_nickName) throws Exception;

	// 랜덤 닉네임 생성
	String makeUserNickName() throws Exception;

	// 아이디 존재 여부 체크
	int checkId(String user_id) throws Exception;

	// 사용자 정보
	UserDto getUserInfo(String user_id) throws Exception;

	byte[] getUserProfile(String user_id) throws Exception;
}
