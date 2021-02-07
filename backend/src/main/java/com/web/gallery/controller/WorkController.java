package com.web.gallery.controller;

import com.web.gallery.dto.CostDto;
import com.web.gallery.dto.HashTagDto;
import com.web.gallery.dto.ScrapDto;
import com.web.gallery.dto.WorkDto;
import com.web.gallery.help.NumberResult;
import com.web.gallery.service.WorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/work")
@Api(value = "artGallery", description = "artGallery")
public class WorkController {

    @Autowired
    private WorkService workService;

    public static final Logger logger = LoggerFactory.getLogger(WorkController.class);

    @ApiOperation(value = "작가가 작품을 등록한다.", response = NumberResult.class,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            notes = "addWork(String work_artistId, String work_title, String work_desc, MultipartFile work_piece, int work_rating[0 or 19], String work_tool, String hashTags)")
    @RequestMapping(value = "/addWork", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> addWork(@RequestParam(value = "work_artistId") String work_artistId,
                                                @RequestParam(value = "work_title") String work_title,
                                                @RequestParam(value = "work_desc") String work_desc,
                                                @RequestParam(value = "work_piece") MultipartFile work_piece,
                                                @RequestParam(value = "work_rating") int work_rating,
                                                @RequestParam(value = "work_tool") String work_tool,
                                                @RequestParam(value = "hashTags") String hashTags) {
        HttpStatus status = null;
        NumberResult ns = new NumberResult();
        WorkDto work = new WorkDto();
        HashTagDto hashtag = new HashTagDto();
        int work_id = 0;
        List<String> listHashTags = Arrays.asList(hashTags.split(","));

        try {
            work.setWork_artistId(work_artistId);
            work.setWork_title(work_title);
            work.setWork_desc(work_desc);
            work.setWork_piece(work_piece.getBytes());
            work.setWork_tool(work_tool);

            if (work_rating != 0) {
                work.setWork_rating(work_rating);
            } else {
                work.setWork_rating(0);
            }

            work_id = workService.addWork(work);

            if (workService.countWorkHashTag(work_id) + listHashTags.size() > 10) {
                ns.setValue("해시태그의 개수가 10개가 넘어갑니다.", 0, "fail");
                status = HttpStatus.ACCEPTED;

                return new ResponseEntity<>(ns, status);
            } else {
                for (String hashtag_name : listHashTags) {
                    hashtag.setHashtag_name(hashtag_name);
                    hashtag.setHashtag_workId(work_id);

                    if (workService.isCheckHashTag(hashtag) == 0) {
                        workService.addHashTag(hashtag);
                    }
                }
            }

            ns.setValue("작품이 등록되었습니다.", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("작품 등록에 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<NumberResult>(ns, status);
    }

    @ApiOperation(value = "하나의 작품의 상세정보를 받아온다", response = Map.class,
            notes = "getWork(String work_id)")
    @RequestMapping(value = "/getWork/{work_id}", method = RequestMethod.GET)
    public ResponseEntity<WorkDto> getWork(@PathVariable("work_id") int work_id) {
        WorkDto work = null;
        HttpStatus status = null;
        List<String> hashTags = null;

        try {
            work = workService.getWork(work_id);
            hashTags = workService.getWorkHashTag(work_id);

            work.setHashtags(hashTags.toArray(new String[0]));

            status = HttpStatus.OK;
        } catch (Exception e) {

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<WorkDto>(work, status);
    }

    @ApiOperation(value = "작품의 내용을 수정한다", response = NumberResult.class,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            notes = "modifyWork(String work_id, String work_title, String work_desc, MultipartFile work_piece, int work_rating[0 or 19], String hashTags)")
    @RequestMapping(value = "/modifyWork", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> modifyWork(@RequestParam(value = "work_id") int work_id,
                                                   @RequestParam(value = "work_title") String work_title,
                                                   @RequestParam(value = "work_desc") String work_desc,
                                                   @RequestParam(value = "work_piece") MultipartFile work_piece,
                                                   @RequestParam(value = "work_rating") int work_rating,
                                                   @RequestParam(value = "hashTags") String hashTags) {
        HttpStatus status = null;
        NumberResult ns = new NumberResult();
        WorkDto work = new WorkDto();

        List<String> listHashTags = Arrays.asList(hashTags.split(","));
        HashTagDto hashtag = new HashTagDto();

        try {
            work.setWork_id(work_id);
            work.setWork_title(work_title);
            work.setWork_desc(work_desc);
            work.setWork_piece(work_piece.getBytes());
            work.setWork_rating(work_rating);

            workService.modifyWork(work);


            ns.setValue("작품이 수정되었습니다.", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("작품 수정에 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<NumberResult>(ns, status);
    }

    @ApiOperation(value = "작품을 삭제한다.", response = NumberResult.class, notes = "deleteWork(int work_id)")
    @RequestMapping(value = "/deleteWork/{work_id}", method = RequestMethod.GET)
    public ResponseEntity<NumberResult> deleteWork(@PathVariable("work_id") int work_id) {
        NumberResult ns = new NumberResult();
        HttpStatus status = null;

        try {
            workService.deleteWork(work_id);

            ns.setValue("작품을 삭제했습니다", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("작품 삭제를 실패했습니다", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<NumberResult>(ns, status);
    }

    @ApiOperation(value = "사이트에 업로드된 작품들 전체를 반환한다.", response = List.class, notes = "getAllWork()")
    @RequestMapping(value = "/getAllWork/{sort}", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAllWork(@PathVariable("sort") int sort) {
        List<WorkDto> works = null;
        HttpStatus status = null;

        try {
            works = workService.getAllWork(sort);

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

    @ApiOperation(value = "나의 작품 목록을 받아온다", response = List.class, notes = "getMyWorks(String work_artistId)")
    @RequestMapping(value = "/getMyWorks/{work_artistId}", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getMyWorks(@PathVariable("work_artistId") String work_artistId) {
        List<WorkDto> works = null;
        HttpStatus status = null;

        try {
            works = workService.getMyWorks(work_artistId);

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

    @ApiOperation(value = "나의 작품 수를 받아온다", response = Integer.class, notes = "getMyWorksCount(String work_artistId")
    @RequestMapping(value = "/getMyWorksCount/{work_artistId}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getMyWorksCount(@PathVariable("work_artistId") String work_artistId) {
        int counts = 0;
        HttpStatus status = null;

        try {
            counts = workService.getMyWorksCount(work_artistId);

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Integer>(counts, status);
    }

    @ApiOperation(value = "팔로우한 작가의 작품 받아오기", response = List.class, notes = "getFollowingWork(String follow_userId)")
    @RequestMapping(value = "/getFollowingWork/{follow_userId}", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getFollowingWork(@PathVariable("follow_userId") String follow_userId) {
        List<WorkDto> works = null;
        HttpStatus status = null;

        try {
            works = workService.getFollowingWork(follow_userId);

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<List<WorkDto>>(works, status);
    }

    @ApiOperation(value = "작품 좋아요(cost 부여)", response = NumberResult.class,
            notes = "giveCostToWork(String cost_userId, int cost_workId)")
    @RequestMapping(value = "/giveCostToWork", method = RequestMethod.GET)
    public ResponseEntity<NumberResult> giveCostToWork(@RequestParam(value = "cost_userId") String cost_userId,
                                                       @RequestParam(value = "cost_workId") int cost_workId) {
        NumberResult ns = new NumberResult();
        HttpStatus status = null;
        CostDto cost = new CostDto();

        try {
            cost.setCost_userId(cost_userId);
            cost.setCost_workId(cost_workId);

            workService.giveCostToWork(cost);

            ns.setValue("작품에 cost를 부여했습니다.", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("작품에 cost를 부여하지 못했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(ns, status);
    }

    @ApiOperation(value = "작품 코스트 부여 여부를 반환한다.", response = Integer.class,
            notes = "isCheckCost(String cost_userId, int cost_id) => 1(좋아요 o), 0(좋아요 x)")
    @RequestMapping(value = "/isCheckCost", method = RequestMethod.GET)
    public ResponseEntity<Integer> isCheckCost(@RequestParam(value = "cost_userId") String cost_userId,
                                               @RequestParam(value = "cost_workId") int cost_workId) {
        int check = 0;
        HttpStatus status = null;
        CostDto cost = new CostDto();

        try {
            cost.setCost_workId(cost_workId);
            cost.setCost_userId(cost_userId);
            check = workService.isCheckCost(cost);

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(check, status);
    }

    @ApiOperation(value = "작품 좋아요(cost 부여) 취소", response = NumberResult.class,
            notes = "clearToWorkCost(String cost_userId, int cost_workId)")
    @RequestMapping(value = "/clearToWorkCost", method = RequestMethod.GET)
    public ResponseEntity<NumberResult> clearToWorkCost(@RequestParam(value = "cost_userId") String cost_userId,
                                                        @RequestParam(value = "cost_workId") int cost_workId) {
        NumberResult ns = new NumberResult();
        HttpStatus status = null;
        CostDto cost = new CostDto();

        try {
            cost.setCost_userId(cost_userId);
            cost.setCost_workId(cost_workId);

            workService.clearToWorkCost(cost);

            ns.setValue("작품 좋아요를 취소했습니다.", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("작품 좋아요 취소를 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(ns, status);
    }

    @ApiOperation(value = "작품을 즐겨찾기한다.", response = NumberResult.class,
            notes = "scrapWork(String scrap_userId, int scrap_workId)")
    @RequestMapping(value = "/scrapWork", method = RequestMethod.GET)
    public ResponseEntity<NumberResult> scrapWork(@RequestParam(value = "scrap_userId") String scrap_userId,
                                                  @RequestParam(value = "scrap_workId") int scrap_workId) {
        NumberResult ns = new NumberResult();
        HttpStatus status = null;
        ScrapDto scrap = new ScrapDto();

        try {
            scrap.setScrap_workId(scrap_workId);
            scrap.setScrap_userId(scrap_userId);

            workService.scrapWork(scrap);

            ns.setValue("작품을 즐겨찾기했습니다", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("작품 즐겨찾기에 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(ns, status);
    }

    @ApiOperation(value = "작품의 즐겨찾기 여부를 반환한다.", response = Integer.class,
            notes = "isCheckScrap(String scrap_userId, int scrap_workId) => 1(좋아요 o), 0(좋아요 x)")
    @RequestMapping(value = "/isCheckScrap", method = RequestMethod.GET)
    public ResponseEntity<Integer> isCheckScrap(@RequestParam(value = "scrap_userId") String scrap_userId,
                                                @RequestParam(value = "scrap_workId") int scrap_workId) {
        int check = 0;
        HttpStatus status = null;
        ScrapDto scrap = new ScrapDto();

        try {
            scrap.setScrap_userId(scrap_userId);
            scrap.setScrap_workId(scrap_workId);

            check = workService.isCheckScarp(scrap);

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(check, status);
    }

    @ApiOperation(value = "작품의 즐겨찾기를 취소한다.", response = NumberResult.class,
            notes = "clearToWorkScrap(String scrap_userId, int scrap_workId)")
    @RequestMapping(value = "/clearToWorkScrap", method = RequestMethod.GET)
    public ResponseEntity<NumberResult> clearToWorkScrap(@RequestParam(value = "scrap_userId") String scrap_userId,
                                                         @RequestParam(value = "scrap_workId") int scrap_workId) {
        NumberResult ns = new NumberResult();
        HttpStatus status = null;
        ScrapDto scrap = new ScrapDto();

        try {
            scrap.setScrap_workId(scrap_workId);
            scrap.setScrap_userId(scrap_userId);

            workService.clearToWorkScrap(scrap);

            ns.setValue("작품 즐겨찾기를 취소했습니다.", 1, "succ");

            status = HttpStatus.OK;
        } catch (Exception e) {
            ns.setValue("작품 즐겨찾기 취소를 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(ns, status);
    }

    @ApiOperation(value = "즐겨찾기한 작품 목록을 반환한다.", response = List.class, notes = "getAllScrapWork(String scrap_userId)")
    @RequestMapping(value = "/getAllScrapWork/{scrap_userId}", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> getAllScrapWork(@PathVariable("scrap_userId") String scrap_userId) {
        List<WorkDto> works = null;
        HttpStatus status = null;

        try {
            works = workService.getAllScrapWork(scrap_userId);

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(works, status);
    }

    @ApiOperation(value = "작품에 지정된 해시태그를 저장한다.", response = NumberResult.class,
            notes = "addHashTag(String hashtags, int hashtag_workId)")
    @RequestMapping(value = "/addHashTag", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> addHashTag(@RequestParam(value = "hashTags") String hashTags,
                                                   @RequestParam(value = "hashtag_workId") int hashtag_workId) {
        NumberResult ns = new NumberResult();
        HttpStatus status = null;
        HashTagDto hashtag = new HashTagDto();

        List<String> listHashTags = Arrays.asList(hashTags.split(","));

        try {
            if (workService.countWorkHashTag(hashtag_workId) + listHashTags.size() > 10) {
                ns.setValue("해시태그의 개수가 10개가 넘어갑니다.", 0, "fail");
                status = HttpStatus.ACCEPTED;

                return new ResponseEntity<>(ns, status);
            } else {
                for (String hashtag_name : listHashTags) {
                    hashtag.setHashtag_name(hashtag_name);
                    hashtag.setHashtag_workId(hashtag_workId);

                    if (workService.isCheckHashTag(hashtag) == 0) {
                        workService.addHashTag(hashtag);
                    }
                }

                ns.setValue("작품 해시태그를 저장했습니다.", 1, "succ");

                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            ns.setValue("해시태그 저장에 실패했습니다.", 0, "fail");

            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(ns, status);
    }

    //작품의 해시태그 목록 반환
    @ApiOperation(value = "작품의 해시태그 목록을 반환한다.", response = List.class, notes = "getWorkHashTag(int hashtag_workId)")
    @RequestMapping(value = "/getWorkHashTag/{hashtag_workId}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getWorkHashTag(@PathVariable("hashtag_workId") int hashtag_workId) {
        List<String> hashtags = null;
        HttpStatus status = null;

        try {
            hashtags = workService.getWorkHashTag(hashtag_workId);

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(hashtags, status);
    }

    @ApiOperation(value = "해시태그 리스트로 작품을 검색한다.", response = List.class, notes = "searchByHashTag(List<String> hashtags)")
    @RequestMapping(value = "/searchByHashTag", method = RequestMethod.GET)
    public ResponseEntity<List<WorkDto>> searchByHashTag(@RequestParam(value = "hashtags") String hashTags) {
        List<WorkDto> works = null;
        HttpStatus status = null;

        List<String> splitHashTags = Arrays.asList(hashTags.split(","));

        try {
            works = workService.searchByHashTag(splitHashTags);

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(works, status);
    }
}