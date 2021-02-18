package com.web.gallery.dao;

import com.web.gallery.dto.ArtistDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistDaoImpl {
    String ns = "com.web.gallery.dao.artistDao.";

    @Autowired
    private SqlSession sqlSession;

    public ArtistDto getArtistInfo(String artist_id) {
        return sqlSession.selectOne(ns + "getArtistInfo", artist_id);
    }

    public int changeArtist(ArtistDto artistDto) {
        sqlSession.insert(ns+"changeArtist", artistDto);                    // 작가 DB에 입력한 작가 정보 삽입
        return sqlSession.update(ns+"updateUserType",artistDto.getArtist_id()); // 사용자 DB에서 user_type 2로 바꿔주기.
    }

    public int leaveArtist(String artist_id) {
        int result = sqlSession.delete(ns+"leaveArtist", artist_id);
        if(result==1){
            return sqlSession.update(ns+"updateUserType",artist_id);
        }
        else return result;
    }

    public int modifyArtist(ArtistDto artistDto) {
        return sqlSession.update(ns+"modifyArtist",artistDto);
    }

    public String getUserNickname(String artist_id) {
        return sqlSession.selectOne(ns+"getUserNickname", artist_id);
    }

    public int changeArtistNicknameToUser(ArtistDto artistDto) {
        return sqlSession.update(ns+"changeArtistNicknameToUser", artistDto);
    }
}
