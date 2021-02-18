package com.web.gallery.controller;

import com.web.gallery.dto.ArtistDto;
import com.web.gallery.help.NumberResult;
import com.web.gallery.service.ArtistService;
import com.web.gallery.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/artist")
@Api(value = "Art Gallery", description = "Art Gallery for Artist")
public class ArtistController {
    public static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistService artistService;
    @Autowired
    private JwtService jwtService;

    @ApiOperation(value = "작가의 아이디로 작가의 정보를 반환한다.", response = ArtistDto.class, notes = "getArtistInfo(artist_id) \n \t return ArtistDto")
    @RequestMapping(value = "/getArtistInfo", method = RequestMethod.GET)
    public ResponseEntity<ArtistDto> getArtistInfo(@RequestParam(value = "artist_id") String artist_id) throws Exception{
        ArtistDto artist = null;
        artist = artistService.getArtistInfo(artist_id);
        if(artist == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ArtistDto>(artist, HttpStatus.OK);
    }
    // 작가 등록
    @ApiOperation(value = "일반 사용자에서 작가로 등록한다.", response = NumberResult.class, notes = "changeArtist(artist_id, artist_bank, artist_account, artist_name, artist_nickName, artist_exp)")
    @RequestMapping(value = "/changeArtist", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> changeArtist(@RequestBody ArtistDto artistDto) throws Exception{
        NumberResult nr = new NumberResult();
        System.out.println("아티스트 닉네임!!!!!!!!!!!!"+artistDto.getArtist_nickName());
        if(artistDto.getArtist_nickName()==null){//가져온 닉네임이 없으면 유저닉네임에 있는 걸 artist 닉네임으로 설정
            String nickname = artistService.getUserNickname(artistDto.getArtist_id());
            artistDto.setArtist_nickName(nickname);
        }
        if(artistService.changeArtist(artistDto)==1){
            nr.setValue("changeArtist", 1, "succ");
            if(artistService.changeArtistNicknameToUser(artistDto)==1){
                nr.setValue("changeArtistNicknameToUser", 1, "succ");
            }
        }
        else
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

    // 작가 등록
    @ApiOperation(value = "작가정보를 수정한다.", response = NumberResult.class, notes = "modifyArtist(ArtistDto)")
    @RequestMapping(value = "/modifyArtist", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> modifyArtist(@RequestBody ArtistDto artistDto) throws Exception{
        NumberResult nr = new NumberResult();

        if(artistService.modifyArtist(artistDto)==1){
            nr.setValue("modifyArtist", 1, "succ");
            if(artistService.changeArtistNicknameToUser(artistDto)==1){
                nr.setValue("changeArtistNicknameToUser", 1, "succ");
            }
        }
        else
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }


    @ApiOperation(value = "작가 등록을 해지한다.", response = NumberResult.class, notes = "leaveArtist(artist_id)")
    @RequestMapping(value = "/leaveArtist", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> leaveArtist(@RequestParam(value = "artist_id") String artist_id) throws Exception{
        NumberResult nr = new NumberResult();
        if(artistService.leaveArtist(artist_id)==1) {
            nr.setValue("leaveArtist", 1, "succ");
        } else{
            new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

}
