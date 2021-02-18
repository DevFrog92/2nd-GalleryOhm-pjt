package com.web.gallery.service;

import com.web.gallery.dto.ArtistDto;

public interface ArtistService {
    ArtistDto getArtistInfo(String artist_id) throws Exception;     // 작가 아이디로 작가정보 받아오기

    int changeArtist(ArtistDto artistDto) throws Exception;           // 일반 사용자를 작가로 변경 (작가 DB에 insert)

    int leaveArtist(String artist_id) throws Exception;              // 작가 등록을 해지한다. 일반 사용자로 변경.

    int modifyArtist(ArtistDto artistDto) throws Exception;          // 작가 정보를 수정한다.

    int changeArtistNicknameToUser(ArtistDto artistDto)throws Exception;   // 작가 등록 시 변경한 nickname을 user nickname에도 적용

    String getUserNickname(String artist_id) throws Exception;   // 작가 등록 시 nickname을 적지 않은 경우, user nickname을 artist nickname으로

}
