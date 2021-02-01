package com.web.gallery.controller;

import com.web.gallery.dto.HashTagDto;
import com.web.gallery.dto.MainGalleryDto;
import com.web.gallery.dto.SubGalleryDto;
import com.web.gallery.dto.UserDto;
import com.web.gallery.help.NumberResult;
import com.web.gallery.service.AdminService;
import com.web.gallery.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/admin")
@Api(value = "artGallery", description = "artGallery")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "사용자 목록을 반환한다.", response = List.class)
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUser() {
        NumberResult ns = new NumberResult();
        List<UserDto> userList = null;
        try {
            userList = adminService.getAllUser();
            ns.setValue("getAllUser", userList.size(), "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("getAllUser", 0, "fail");
            return new ResponseEntity<List<UserDto>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<UserDto>>(userList, HttpStatus.OK);
    }

    @ApiOperation(value = "전체 해시태그 목록을 조회한다.", response = List.class)
    @RequestMapping(value = "/getAllHashTag", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllHashTag() {
        NumberResult ns = new NumberResult();
        List<String> hashTagList = null;
        try {
            hashTagList = adminService.getAllHashTag();
            ns.setValue("getAllHashTag", hashTagList.size(), "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("getAllHashTag", 0, "fail");
            return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<String>>(hashTagList, HttpStatus.OK);
    }

    @ApiOperation(value = "작품에 연령등급을 부여한다 ( -> Adult)", response = NumberResult.class)
    @RequestMapping(value = "/giveRating", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> giveRating(@RequestBody int work_id) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            adminService.giveRating(work_id);
            ns.setValue("giveRating", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("giveRating", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "작품에 부여된 연령등급을 해지한다 ( -> Baby)", response = NumberResult.class)
    @RequestMapping(value = "/clearRating", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> clearRating(@RequestBody int work_id) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            adminService.clearRating(work_id);
            ns.setValue("clearRating", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("clearRating", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "메인관 전시 작품(전시관) 추천 키워드 추가", response = NumberResult.class)
    @RequestMapping(value = "/addMainGalleryHashTag", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> addMainGalleryHashTag(@RequestBody String keyword_name) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            adminService.addMainGalleryHashTag(keyword_name);
            ns.setValue("addMainGalleryHashTag", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("addMainGalleryHashTag", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "메인관 전시 작품(전시관) 추천 키워드 삭제", response = NumberResult.class)
    @RequestMapping(value = "/deleteMainGalleryHashTag", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> deleteMainGalleryHashTag(@RequestBody String keyword_name) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            adminService.deleteMainGalleryHashTag(keyword_name);
            ns.setValue("deleteMainGalleryHashTag", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("deleteMainGalleryHashTag", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "메인관 전시 작품(전시관) 추천 키워드 전체 목록 조회", response = List.class)
    @RequestMapping(value = "/getMainGalleryHashTag", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getMainGalleryHashTag() {
        NumberResult ns = new NumberResult();
        List<String> keywordList = null;
        try {
            keywordList = adminService.getMainGalleryHashTag();
            ns.setValue("getMainGalleryHashTag", keywordList.size(), "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("getMainGalleryHashTag", 0, "fail");
            return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<String>>(keywordList, HttpStatus.OK);
    }

    @ApiOperation(value = "작품에 태그된 해시태그 삭제", response = NumberResult.class)
    @RequestMapping(value = "/deleteHashTagFromWork", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> deleteHashTagFromWork(@RequestBody HashTagDto hashTagDto) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            adminService.deleteHashTagFromWork(hashTagDto);
            ns.setValue("deleteHashTagFromWork", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("deleteHashTagFromWork", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "해시태그 전체 작품에서 삭제", response = NumberResult.class)
    @RequestMapping(value = "/deleteHashTagFromTotal", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> deleteHashTagFromTotal(@RequestBody HashTagDto hashTagDto) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            adminService.deleteHashTagFromTotal(hashTagDto);
            ns.setValue("deleteHashTagFromTotal", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("deleteHashTagFromTotal", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "메인관 전시 작품(전시관) 선정 후 목록 조회 <전시관 아이디>", response = List.class)
    @RequestMapping(value = "/renewMainGallery", method = RequestMethod.GET)
    public ResponseEntity<List<MainGalleryDto>> renewMainGallery() {
        NumberResult ns = new NumberResult();
        List<MainGalleryDto> mainGalleryList = null;
        try {
            adminService.renewMainGallery_algorithm();
            mainGalleryList = adminService.renewMainGallery();
            ns.setValue("renewMainGallery", mainGalleryList.size(), "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("renewMainGallery", 0, "fail");
            return new ResponseEntity<List<MainGalleryDto>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<MainGalleryDto>>(mainGalleryList, HttpStatus.OK);
    }

    @ApiOperation(value = "서브관 전시 작품 선정 후 목록 조회 <작품 아이디>", response = List.class)
    @RequestMapping(value = "/renewSubGallery", method = RequestMethod.GET)
    public ResponseEntity<List<SubGalleryDto>> renewSubGallery() {
        NumberResult ns = new NumberResult();
        List<SubGalleryDto> subGalleryList = null;
        try {
            adminService.renewSubGallery_delete();
            adminService.renewSubGallery_algorithm();
            subGalleryList = adminService.renewSubGallery();
            ns.setValue("renewSubGallery", subGalleryList.size(), "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("renewSubGallery", 0, "fail");
            return new ResponseEntity<List<SubGalleryDto>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<SubGalleryDto>>(subGalleryList, HttpStatus.OK);
    }

}
