package com.web.gallery.service;

import com.web.gallery.dto.ArtistDto;

public interface ArtistService {
    ArtistDto getArtistInfo(String artist_id) throws Exception;     // 작가 아이디로 작가정보 받아오기

    int changeArtist(ArtistDto artistDto) throws Exception;         // 일반 사용자를 작가로 변경 (작가 DB에 insert)

    int leaveArtist(String artist_id) throws Exception;               // 작가 등록을 해지한다. 일반 사용자로 변경.
}
