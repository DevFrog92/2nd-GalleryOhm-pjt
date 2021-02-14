package com.web.gallery.controller;

import com.web.gallery.dto.ArtistDto;
import com.web.gallery.dto.GalleryDto;
import com.web.gallery.dto.UserDto;
import com.web.gallery.dto.WorkDto;
import com.web.gallery.help.NumberResult;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/gallery")
@Api(value = "Art Gallery", description = "Art Gallery")
public class GalleryController {
    public static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    private GalleryService galleryService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private WorkService workService;

    /**** 작가 갤러리 ****/
    @ApiOperation(value = "작가의 개인 전시관을 추가한다.(작품들과 함께) ", response = NumberResult.class, notes = "addArtistGallery(gallery_name, gallery_desc, gallery_name, gallery_mainWorkId, [List]input_workId)")
    @RequestMapping(value = "/addArtistGallery", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> addArtistGallery(@RequestParam(value = "gallery_name") String gallery_name,
                                                         @RequestParam(value = "gallery_desc") String gallery_desc,
                                                         @RequestParam(value = "gallery_artistId") String gallery_artistId,
                                                         @RequestParam(value = "gallery_mainWorkId") int gallery_mainWorkId,
                                                         @RequestParam(value = "gallery_workIdList") List<Integer> gallery_workIdList) throws Exception {
        NumberResult nr = new NumberResult();
        System.out.println(gallery_workIdList.toString());
        if (galleryService.addArtistGallery(gallery_name, gallery_desc, gallery_artistId, gallery_mainWorkId) == 1) {
            int gallery_id = galleryService.getGalleryId(gallery_mainWorkId);
            if (galleryService.addArtistGalleryDetail(gallery_workIdList) >= 1) {    // 작품에 추가.
                boolean isCheck = false;
                List<HashMap<String, Integer>> ratingList = (List<HashMap<String, Integer>>) galleryService.isAdultGallery(gallery_id);
                for (int i = 0; i < ratingList.size(); i++)
                    if (ratingList.get(i).get("work_rating") == 19)
                        isCheck = true;
                if (isCheck) {
                    // 갤러리 작품 중 연령 제한 작품이 포함되어 있는 경우
                    galleryService.updateGalleryForAdult(gallery_id);
                }
                nr.setValue("addArtistGallery", 1, "succ");
            }
        } else {
            nr.setValue("addArtistGallery", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

    @ApiOperation(value = "작가의 개인 전시관을 수정한다.(작품들과 함께) ", response = NumberResult.class, notes = "modifyArtistGallery(gallery_id, gallery_name, gallery_desc, gallery_name, gallery_mainWorkId, [List]input_workId)")
    @RequestMapping(value = "/modifyArtistGallery", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> modifyArtistGallery(@RequestParam(value = "gallery_id") int gallery_id,
                                                            @RequestParam(value = "gallery_name") String gallery_name,
                                                            @RequestParam(value = "gallery_desc") String gallery_desc,
                                                            @RequestParam(value = "gallery_artistId") String gallery_artistId,
                                                            @RequestParam(value = "gallery_mainWorkId") int gallery_mainWorkId,
                                                            @RequestParam(value = "gallery_workIdList") List<Integer> gallery_workIdList) {
        NumberResult nr = new NumberResult();
        if (galleryService.modifyArtistGallery(gallery_id, gallery_name, gallery_desc, gallery_artistId, gallery_mainWorkId) == 1) { // 갤러리 DB에서 수정
            if (galleryService.deleteArtistGalleryDetail(gallery_id) >= 0) { // gallery_id에 해당하는 모든 작품들 gallery_detail에서 삭제
                if (galleryService.addArtistGalleryDetail(gallery_workIdList) >= 0) {    // gallerydetail DB에 해당 전시관 작품 추가.
                    nr.setValue("modifyArtistGallery", 1, "succ");
                } else {
                    return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
                }

            } else {
                return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }


    @ApiOperation(value = "작가의 개인 전시관을 삭제한다.", response = NumberResult.class, notes = "deleteArtistGallery(gallery_id)")
    @RequestMapping(value = "/deleteArtistGallery/{gallery_id}", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> deleteArtistGallery(@PathVariable int gallery_id) throws Exception {
        NumberResult nr = new NumberResult();
        if (galleryService.deleteArtistGallery(gallery_id) == 1) {
            nr.setValue("deleteArtistGallery", 1, "succ");
        } else {
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

    @ApiOperation(value = "해당 gallery_id의 갤러리 정보를 조회한다.", response = GalleryDto.class, notes = "getGallery(gallery_id)")
    @RequestMapping(value = "/getGallery/{gallery_id}", method = RequestMethod.GET)
    public ResponseEntity<GalleryDto> getGallery(@PathVariable("gallery_id") int gallery_id) throws Exception {
        GalleryDto gallery = null;
        HttpStatus status;
        try {
            gallery = galleryService.getGallery(gallery_id);
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<GalleryDto>(gallery, status);
    }

    /**** 메인 갤러리 ****/
    @ApiOperation(value = "메인 갤러리의 전시관 목록들을 조회한다.", response = List.class, notes = "getAllMainGallery()")
    @RequestMapping(value = "/getAllMainGallery", method = RequestMethod.GET)
    public ResponseEntity<List<GalleryDto>> getAllMainGallery() throws Exception {
        List<GalleryDto> gallerys = galleryService.getAllMainGallery();
        if (gallerys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<GalleryDto>>(gallerys, HttpStatus.OK);
    }

    @ApiOperation(value = "메인 갤러리의 전시관을 전시관 아이디로 삭제한다. ", response = NumberResult.class, notes = "deleteGalleryToMainGallery(maingallery_id, maingallery_artistId)")
    @RequestMapping(value = "/deleteGalleryToMainGallery/{mainGallery_galleryId}", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> deleteGalleryToMainGallery(@PathVariable int mainGallery_galleryId) throws Exception {
        NumberResult nr = new NumberResult();
        if (galleryService.deleteGalleryToMainGallery(mainGallery_galleryId) == 1) {
            nr.setValue("deleteGalleryToMainGallery", 1, "succ");
        } else {
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

    /**** 서브 갤러리 ****/
    @ApiOperation(value = "서브 갤러리의 작품 목록들을 조회한다.", response = List.class, notes = "getAllSubGallery()")
    @RequestMapping(value = "/getAllSubGallery", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAllSubGallery() throws Exception {
        List<WorkDto> works = galleryService.getAllSubGallery();
        if (works.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<WorkDto>>(works, HttpStatus.OK);
    }

    /**** 서브 갤러리 홀수 번호 작품 반환****/
    @ApiOperation(value = "서브 갤러리의 홀수 작품 목록들을 조회한다.", response = List.class, notes = "getAllSubGallery()")
    @RequestMapping(value = "/getAllSubGallery_odd", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAllSubGallery_odd() throws Exception {
        List<WorkDto> works = galleryService.getAllSubGallery_odd();
        if (works.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<WorkDto>>(works, HttpStatus.OK);
    }

    /**** 서브 갤러리 짝수 번호 작품 반환****/
    @ApiOperation(value = "서브 갤러리의 홀수 작품 목록들을 조회한다.", response = List.class, notes = "getAllSubGallery()")
    @RequestMapping(value = "/getAllSubGallery_even", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAllSubGallery_even() throws Exception {
        List<WorkDto> works = galleryService.getAllSubGallery_even();
        if (works.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<WorkDto>>(works, HttpStatus.OK);
    }

    @ApiOperation(value = "서브 갤러리의 작품을 작품 아이디로 삭제한다.", response = NumberResult.class, notes = "deleteWorkToSubGallery(subGallery_workId)")
    @RequestMapping(value = "/deleteWorkToSubGallery/{subGallery_workId}", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> deleteWorkToSubGallery(@PathVariable int subGallery_workId) throws Exception {
        NumberResult nr = new NumberResult();
        if (galleryService.deleteWorkToSubGallery(subGallery_workId) == 1) {
            nr.setValue("deleteWorkToSubGallery", 1, "succ");
        } else {
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

    /**** 전시관 관련 기타 기능 ****/
    @ApiOperation(value = "전시관 속 작품들 목록을 받아온다.", response = List.class, notes = "getArtistGallery(galleryDetail_galleryId) \n return List<WorkDto>")
    @RequestMapping(value = "/getArtistGallery/{galleryDetail_galleryId}", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getArtistGallery(@PathVariable int galleryDetail_galleryId) throws Exception {
        List<WorkDto> worksInGallery = galleryService.getArtistGallery(galleryDetail_galleryId);
        if (worksInGallery.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<WorkDto>>(worksInGallery, HttpStatus.OK);
    }


    @ApiOperation(value = "작가의 전시관 목록을 받아온다.", response = List.class, notes = "getMyGallery(gallery_artistId) \n return List<GalleryDto>")
    @RequestMapping(value = "/getMyGallery/{gallery_artistId}", method = RequestMethod.GET)
    public ResponseEntity<List<GalleryDto>> getMyGallery(@PathVariable String gallery_artistId) throws Exception {
        List<GalleryDto> myGallery = galleryService.getMyGallery(gallery_artistId);
        if (myGallery.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<GalleryDto>>(myGallery, HttpStatus.OK);
    }


    @ApiOperation(value = "전시관 발자국(좋아요)을 추가한다.", response = NumberResult.class, notes = "giveFootPrintToGallery(footprint_galleryId, footprint_userId)")
    @RequestMapping(value = "/giveFootPrintToGallery/{footPrint_galleryId}", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> giveFootPrintToGallery(@PathVariable int footPrint_galleryId, @RequestBody String footPrint_userId) throws Exception {
        NumberResult nr = new NumberResult();
        galleryService.giveFootPrintToGallery(footPrint_galleryId, footPrint_userId);
        nr.setValue("giveFootPrintToGallery", 1, "succ");
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

    @ApiOperation(value = "전시관 발자국(좋아요)을 반환한다.", response = NumberResult.class, notes = "getFootPrintToGallery()")
    @RequestMapping(value = "/getFootPrintToGallery/{footPrint_galleryId}", method = RequestMethod.GET)
    public ResponseEntity<NumberResult> getFootPrintToGallery(@PathVariable int footPrint_galleryId) throws Exception {
        NumberResult nr = new NumberResult();
        int count = galleryService.getFootPrintToGallery(footPrint_galleryId);
        nr.setValue("getFootPrintToGallery", count, "succ");
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자의 전시관 발자국(좋아요) 여부를 받아온다.", response = NumberResult.class, notes = "giveFootPrintToGallery(footprint_galleryId, footprint_userId)")
    @RequestMapping(value = "/isFootPrintToGallery/{footPrint_galleryId}", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> isFootPrintToGallery(@PathVariable int footPrint_galleryId, @RequestBody String footPrint_userId) throws Exception {
        NumberResult nr = new NumberResult();
        int isCheck = galleryService.isFootPrintToGallery(footPrint_galleryId, footPrint_userId);
        nr.setValue("isFootPrintToGallery", isCheck, "succ");
        return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
    }

    /*** 성인 갤러리 목록 반환 ***/
    @ApiOperation(value = "성인 갤러리 작품 목록 반환.", response = List.class, notes = "getAdultGallery \n return List<WorkDto>")
    @RequestMapping(value = "/getAdultGallery", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAdultGallery() throws Exception {
        List<WorkDto> adultGallery = galleryService.getAdultGallery();
        if (adultGallery.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<WorkDto>>(adultGallery, HttpStatus.OK);
    }

    /*** 성인 갤러리 홀수 목록 반환 ***/
    @ApiOperation(value = "성인 갤러리 작품 목록 반환.", response = List.class, notes = "getAdultGallery \n return List<WorkDto>")
    @RequestMapping(value = "/getAdultGallery_odd", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAdultGallery_odd() throws Exception {
        List<WorkDto> adultGallery_odd = galleryService.getAdultGallery_odd();
        if (adultGallery_odd.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<WorkDto>>(adultGallery_odd, HttpStatus.OK);
    }

    /*** 성인 갤러리 짝수 목록 반환 ***/
    @ApiOperation(value = "성인 갤러리 작품 목록 반환.", response = List.class, notes = "getAdultGallery \n return List<WorkDto>")
    @RequestMapping(value = "/getAdultGallery_even", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAdultGallery_even() throws Exception {
        List<WorkDto> adultGallery_even = galleryService.getAdultGallery_even();
        if (adultGallery_even.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<WorkDto>>(adultGallery_even, HttpStatus.OK);
    }

    /*** 전체 갤러리 반환 ***/
    @ApiOperation(value = "전체 갤러리 목록 반환", response = List.class, notes = "getAllGallery()")
    @RequestMapping(value = "/getAllGallery", method = RequestMethod.GET)
    public ResponseEntity<List<GalleryDto>> getAllGallery() {
        HttpStatus status = null;
        List<GalleryDto> gallerys = null;
        WorkDto work = null;

        try {
            gallerys = galleryService.getAllGallery();

            for (int i = 0; i < gallerys.size(); i++) {
                work = workService.getWork(gallerys.get(i).getGallery_mainWorkId());
                gallerys.get(i).setGallery_mainWork(work.getWork_piece());
            }

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(gallerys, status);
    }

    /*** 전체 갤러리 반환(성인용) ***/
    @ApiOperation(value = "전체 갤러리 목록 반환(성인용)", response = List.class, notes = "getAllAdultGallery()")
    @RequestMapping(value = "/getAllAdultGallery", method = RequestMethod.GET)
    public ResponseEntity<List<GalleryDto>> getAllAdultGallery() {
        HttpStatus status = null;
        List<GalleryDto> gallerys = null;
        WorkDto work = null;

        try {
            gallerys = galleryService.getAllAdultGallery();

            for (int i = 0; i < gallerys.size(); i++) {
                work = workService.getWork(gallerys.get(i).getGallery_mainWorkId());
                gallerys.get(i).setGallery_mainWork(work.getWork_piece());
            }

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(gallerys, status);
    }
}
