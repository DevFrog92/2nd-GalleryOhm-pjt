package com.web.gallery.service;

import com.web.gallery.dao.ArtistDaoImpl;
import com.web.gallery.dto.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    ArtistDaoImpl artistDao;

    @Override
    public ArtistDto getArtistInfo(String artist_id) throws Exception {
        return artistDao.getArtistInfo(artist_id);
    }

    @Override
    public int changeArtist(ArtistDto artistDto) throws Exception {
        return artistDao.changeArtist(artistDto);
    }

    @Override
    public int leaveArtist(String artist_id) throws Exception {
        return artistDao.leaveArtist(artist_id);
    }
}
