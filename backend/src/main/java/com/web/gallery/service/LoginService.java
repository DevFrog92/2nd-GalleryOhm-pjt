package com.web.gallery.service;

import com.web.gallery.dto.UserDto;

public interface LoginService {

    UserDto login(UserDto user) throws Exception;          // 로그인

    String findId(UserDto user) throws Exception;          // 아이디 찾기(이름, 이메일 입력받음)

    String findPassword(UserDto user) throws Exception;    // 비밀번호 찾기 (아이디,이메일, 연락처를 입력받아고 비밀번호 이메일로 전송)
}
