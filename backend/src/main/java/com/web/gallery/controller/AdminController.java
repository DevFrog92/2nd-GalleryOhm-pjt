package com.web.gallery.controller;

import com.web.gallery.dto.*;
import com.web.gallery.help.NumberResult;
import com.web.gallery.service.AdminService;
import com.web.gallery.service.GalleryService;
import com.web.gallery.service.JwtService;
import com.web.gallery.service.WorkService;
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

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/admin")
@Api(value = "artGallery", description = "artGallery")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private GalleryService galleryService;

    public static final Logger logger = LoggerFactory.getLogger(AdminController.class);

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

    @ApiOperation(value = "작품 전체를 반환한다.", response = List.class, notes = "getAllWork()")
    @RequestMapping(value = "/getAllWork", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAllWork() {
        List<WorkDto> works = null;
        HttpStatus status = null;

        try {
            works = adminService.getAllWork();

            if (works != null) {

                status = HttpStatus.OK;
            } else {
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<List<WorkDto>>(works, status);
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
    @RequestMapping(value = "/addMainGalleryKeyword", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> addMainGalleryKeyword(@RequestParam(value = "keywordList") List<String> keywordList) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            adminService.deleteMainGalleryKeyword();
            adminService.addMainGalleryKeyword(keywordList);
            ns.setValue("addMainGalleryKeyword", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("addMainGalleryKeyword", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "메인관 전시 작품(전시관) 추천 키워드 삭제", response = NumberResult.class)
    @RequestMapping(value = "/deleteMainGalleryKeyword", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> deleteMainGalleryKeyword(@RequestBody String keyword_name) throws Exception {
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
    @RequestMapping(value = "/getMainGalleryKeyword", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getMainGalleryKeyword() {
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
    public ResponseEntity<NumberResult> deleteHashTagFromTotal(@RequestParam(value = "hasgtagList") List<String> hasgtagList) throws Exception {
        NumberResult ns = new NumberResult();
        try {
//            adminService.deleteMainGalleryKeyword();
//            adminService.addMainGalleryKeyword(keywordList);
            adminService.deleteHashTagFromTotal();
            adminService.addHashTagFromTotal(hasgtagList);
            ns.setValue("deleteHashTagFromTotal", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("deleteHashTagFromTotal", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "메인관 전시 작품(전시관) 선정", response = NumberResult.class)
    @RequestMapping(value = "/renewMainGallery", method = RequestMethod.GET)
    public ResponseEntity<NumberResult> renewMainGallery() {
        NumberResult ns = new NumberResult();

        try {
            adminService.renewMainGallery_delete();
            adminService.renewMainAdultGallery_delete();
            adminService.renewMainGallery_algorithm(); // insert
            adminService.renewMainAdultGallery_algorithm(); // insert

            ns.setValue("renewMainGallery", 10, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("renewMainGallery", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    /**** 메인 갤러리 목록 조회 ****/
    @ApiOperation(value = "메인 갤러리의 전시관 목록들을 조회한다.", response = List.class, notes = "getAllMainGallery()")
    @RequestMapping(value = "/getAllMainGallery", method = RequestMethod.GET)
    public ResponseEntity<List<GalleryDto>> getAllMainGallery() throws Exception {
        List<GalleryDto> gallerys = galleryService.getAllMainGallery();
        if (gallerys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<GalleryDto>>(gallerys, HttpStatus.OK);
    }

    /**** 메인 갤러리 (연령 제한 작품 포함) 목록 조회 ****/
    @ApiOperation(value = "메인 갤러리의 전시관 목록들을 조회한다. (19 Adult)", response = List.class, notes = "getAllMainAdultGallery()")
    @RequestMapping(value = "/getAllMainAdultGallery", method = RequestMethod.GET)
    public ResponseEntity<List<GalleryDto>> getAllMainAdultGallery() throws Exception {
        List<GalleryDto> adultGallerys = galleryService.getAllMainAdultGallery();
        if (adultGallerys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<GalleryDto>>(adultGallerys, HttpStatus.OK);
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