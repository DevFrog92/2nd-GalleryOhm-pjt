package com.web.gallery.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.web.gallery.service.JwtService;

import com.web.gallery.dto.UserDto;
import com.web.gallery.help.NumberResult;
import com.web.gallery.service.UserService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
@Api(value = "artGallery", description = "artGallery")
public class UserController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "[회원가입] 회원 정보를 입력하여 회원가입한다.", response = NumberResult.class,
			notes = "Try it out => joinUser(user_email, user_id, user_name, user_password, user_tel)")
	@RequestMapping(value = "/joinUser", method = RequestMethod.POST)
	private ResponseEntity<NumberResult> joinUser(@RequestBody UserDto user) throws SQLException {
		NumberResult ns = new NumberResult();
		try {
			userService.joinUser(user);
			ns.setValue("joinUser", 1, "succ");
		} catch (Exception e) {
			e.printStackTrace();
			ns.setValue("joinUser", 0, "fail");
			return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
	}

	@ApiOperation(value = "[회원 정보 수정] 수정하고자 하는 회원 정보를 수정하고, 새로운 토큰을 반환한다.", response = Map.class,
			notes = "Try it out => modifyUser(user_email, user_name, user_password, user_tel, user_nickName, user_profile, user_birth)")
	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> modifyUser(@RequestParam(value = "user_id") String user_id,
												   @RequestParam(value = "user_email") String user_email,
												   @RequestParam(value = "user_password") String user_password,
												   @RequestParam(value = "user_tel") String user_tel,
												   @RequestParam(value = "user_birth") String user_birth,
												   @RequestParam(value = "user_nickName") String user_nickName,
												   @RequestParam(value = "user_name") String user_name,
												   @RequestParam(value = "user_profile") MultipartFile user_profile) throws SQLException, IOException {
		NumberResult ns = new NumberResult();
		Map<String, Object> resultMap = new HashMap<>();
		UserDto user = new UserDto();

		try {
			user.setUser_id(user_id);
			user.setUser_email(user_email);
			user.setUser_name(user_name);
			user.setUser_password(user_password);
			user.setUser_nickName(user_nickName);
			user.setUser_birth(user_birth);
			user.setUser_tel(user_tel);
			user.setUser_profile(user_profile.getBytes());

			userService.modifyUser(user);
			ns.setValue("modifyUser", 1, "succ");

			String token = jwtService.create(user);
			logger.trace("로그인 토큰정보 : {}", token);

			// User 정보 Map에 저장
			resultMap.put("auth_token", token);
			resultMap.put("user_id", user.getUser_id());
			resultMap.put("user_name", user.getUser_name());
			resultMap.put("user_email", user.getUser_email());
			resultMap.put("user_nickName", user.getUser_nickName());
			resultMap.put("user_tel", user.getUser_tel());
			resultMap.put("user_birth", user.getUser_birth());
			resultMap.put("user_type", user.getUser_type());
			resultMap.put("user_createDate", user.getUser_createDate());
			resultMap.put("user_profile", user.getUser_profile());
			resultMap.put("user_about", user.getUser_about());
		} catch (Exception e) {
			e.printStackTrace();
			ns.setValue("modifyUser", 0, "fail");
			return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
	}

	@ApiOperation(value = "[회원 탈퇴] 사용자 정보 토큰을 통해 회원 탈퇴를 진행한다.", response = NumberResult.class)
	@RequestMapping(value = "/leaveUser", method = RequestMethod.GET)
	public ResponseEntity<NumberResult> leaveUser(@RequestParam(value = "user_id") String user_id) throws SQLException {
		NumberResult ns = new NumberResult();

		try {
			userService.leaveUser(user_id);
			ns.setValue("leaveUser", 1, "succ");
		} catch (Exception e) {
			e.printStackTrace();
			ns.setValue("leaveUser", 0, "fail");
			return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
	}

	@ApiOperation(value = "[비밀번호 확인] 사용자가 입력한 비밀번호가 DB 비밀번호와 일치하는지 확인한다.", response = NumberResult.class,
			notes = "Try it out => checkPassword(input_password)")
	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	private ResponseEntity<NumberResult> checkPassword(@RequestParam(value = "user_id") String user_id,
													   @RequestParam(value = "input_password") String input_password) {
		NumberResult ns = new NumberResult();
		try {
			if (userService.checkPassword(user_id, input_password) == 1) {
				ns.setValue("checkPassword", 1, "succ");
			} else {
				ns.setValue("checkPassword", 0, "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
	}

	@ApiOperation(value = "[아이디 중복 체크] 사용자가 입력한 아이디의 중복을 체크한다.", response = NumberResult.class,
			notes = "Try it out => checkId(input_id)")
	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	private ResponseEntity<NumberResult> checkId(@RequestParam(value = "input_id") String input_id) {
		NumberResult ns = new NumberResult();
		try {
			if (userService.checkId(input_id) == 0) {
				ns.setValue("checkId", 1, "succ");
			} else {
				ns.setValue("checkId", 0, "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
	}

	@ApiOperation(value = "[사용자 정보 반환] 사용자의 아이디를 통해 사용자 정보를 Map 형태로 반환한다.", response = Map.class,
			notes = "Try it out => getUserInfo(user_id)")
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	private ResponseEntity<UserDto> getUserInfo(@RequestParam(value = "user_id") String user_id) throws Exception {
		UserDto user = null;
		try {
			if (userService.checkId(user_id) == 1) {
				user = userService.getUserInfo(user_id);
			} else {
				return new ResponseEntity<UserDto>(user, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error("사용자 정보 반환 실패 : {}", e);
			return new ResponseEntity<UserDto>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UserDto>(user, HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "[사용자 프로필 사진 정보 반환] 사용자의 아이디를 통해 사용자 프로필 사진을 BLOB 형태로 반환한다.", response = byte[].class,
			notes = "Try it out => getUserProfile(user_id)")
	@RequestMapping(value = "/getUserProfile", method = RequestMethod.GET)
	private ResponseEntity<byte[]> getUserProfile(@RequestParam(value = "user_id") String user_id) throws Exception {
		byte[] user_profile = null;
		try {
			if (userService.checkId(user_id) == 1) {
				user_profile = userService.getUserProfile(user_id);
			} else {
				return new ResponseEntity<byte[]>(user_profile, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error("사용자 정보 반환 실패 : {}", e);
			return new ResponseEntity<byte[]>(user_profile, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<byte[]>(user_profile, HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "[닉네임 직접 수정] 사용자의 닉네임을 입력을 통해 수정한다.", response = NumberResult.class,
	notes = "Try it out => modifyUserNickName(input_nickName) <front : id, input_nickName>")
	@RequestMapping(value = "/modifyUserNickName", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> modifyUserNickname(@RequestParam(value = "user_id") String user_id,
														   @RequestParam(value = "input_nickName") String input_nickName) {
		NumberResult ns = new NumberResult();
		try {
			userService.modifyUserNickName(user_id, input_nickName);
			ns.setValue("modifyUserNickName", 1, "succ");
		} catch (Exception e) {
			e.printStackTrace();
			ns.setValue("modifyUserNickName", 0, "fail");
			return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
	}

	@ApiOperation(value = "[닉네임 랜덤 수정] 사용자의 닉네임을 랜덤으로 부여한다.", response = NumberResult.class,
			notes = "Try it out => makeUserNickName()")
	@RequestMapping(value = "/makeUserNickName", method = RequestMethod.POST)
	public ResponseEntity<String> makeUserNickName() {
		NumberResult ns = new NumberResult();
		String randomNickName = null;
		try {
			randomNickName = userService.makeUserNickName();
			ns.setValue("makeUserNickName", 1, "succ");
		} catch (Exception e) {
			e.printStackTrace();
			ns.setValue("makeUserNickName", 0, "fail");
			return new ResponseEntity<String>(randomNickName, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(randomNickName, HttpStatus.OK);
	}

	@ApiOperation(value = "[소개글 수정] 사용자의 소개글을 수정한다.", response = NumberResult.class,
			notes = "Try it out => modifyUserAbout(user_id, user_about) ")
	@RequestMapping(value = "/modifyUserAbout", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> modifyUserAbout(@RequestParam(value = "user_id") String user_id,
														@RequestParam(value = "user_about") String user_about) {
		NumberResult ns = new NumberResult();
		UserDto user = new UserDto();

		try {
			user.setUser_id(user_id);
			user.setUser_about(user_about);
			if (userService.checkId(user_id) == 1) {
				userService.modifyUserAbout(user);
				ns.setValue("modifyUserAbout", 1, "succ");
			} else {
				ns.setValue("modifyUserAbout", 0, "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ns.setValue("modifyUserAbout", 0, "fail");
			return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<NumberResult>(ns, HttpStatus.OK);
	}
}
