package com.web.gallery.controller;

import com.web.gallery.dto.CostDto;
import com.web.gallery.dto.FollowDto;
import com.web.gallery.help.NumberResult;
import com.web.gallery.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/follow")
@Api(value = "artGallery", description = "artGallery")
public class FollowController {

    @Autowired
    private FollowService followService;

    @ApiOperation(value = "팔로우 하기", response = NumberResult.class)
    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> follow(@RequestBody FollowDto follow) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            followService.follow(follow);
            ns.setValue("follow", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("follow", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "팔로우 취소하기", response = NumberResult.class)
    @RequestMapping(value = "/cancelFollow", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> cancelFollow(@RequestBody FollowDto follow) throws Exception {
        NumberResult ns = new NumberResult();
        try {
            followService.cancelFollow(follow);
            ns.setValue("cancelFollow", 1, "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("cancelFollow", 0, "fail");
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
    }

    @ApiOperation(value = "팔로잉 목록 조회", response = List.class)
    @RequestMapping(value = "/getAllMyFollowing", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllMyFollowing(@RequestParam(value = "user_id") String user_id) {
        NumberResult ns = new NumberResult();
        List<String> followingList = null;
        try {
            followingList = followService.getAllMyFollowing(user_id);
            ns.setValue("getAllMyFollowing", followingList.size(), "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("getAllMyFollowing", 0, "fail");
            return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<String>>(followingList, HttpStatus.OK);
    }


    @ApiOperation(value = "팔로워 목록 조회", response = List.class)
    @RequestMapping(value = "/getAllMyFollower", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllMyFollower(@RequestParam(value = "user_id") String user_id) {
        NumberResult ns = new NumberResult();
        List<String> followerList = null;
        try {
            followerList = followService.getAllMyFollower(user_id);
            ns.setValue("getAllMyFollower", followerList.size(), "succ");
        } catch (Exception e) {
            e.printStackTrace();
            ns.setValue("getAllMyFollower", 0, "fail");
            return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<String>>(followerList, HttpStatus.OK);
    }

    @ApiOperation(value = "팔로우 여부 반환", response = Integer.class,
            notes = "isCheckFollow(String follow_artistId, String follow_userId) => 1(팔로우 o), 0(팔로우 x)")
    @RequestMapping(value = "/isCheckFollow", method = RequestMethod.GET)
    public ResponseEntity<Integer> isCheckFollow(@RequestParam(value = "follow_artistId") String follow_artistId,
                                               @RequestParam(value = "follow_userId") String follow_userId) {
        int check = 0;
        HttpStatus status = null;
        FollowDto follow = new FollowDto();

        try {
            follow.setFollow_artistId(follow_artistId);
            follow.setFollow_userId(follow_userId);
            check = followService.isCheckFollow(follow);

            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(check, status);
    }
}
