package com.web.gallery.controller;

import com.web.gallery.dto.UserDto;
import com.web.gallery.help.NumberResult;
import com.web.gallery.service.JwtService;
import com.web.gallery.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/login")
@Api(value = "artGallery", description = "artGallery")
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JavaMailSender javaMailSender;

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "로그인을 시도하고 로그인 토큰 정보를 Map 형태로 받아온다.", response = Map.class,
            notes = "login(String user_id, String user_password)")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ResponseEntity<Map<String, Object>> login(@RequestParam(value = "user_id") String user_id,
                                                      @RequestParam(value = "user_password") String user_password) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        UserDto user = new UserDto();

        try {
            user.setUser_id(user_id);
            user.setUser_password(user_password);

            UserDto loginUser = loginService.login(user);

            if (loginUser != null) {
                // jwt.io에서 확인
                // 로그인 성공했다면 토큰을 생성한다.
                String token = jwtService.create(loginUser);
                logger.trace("로그인 토큰정보 : {}", token);

                // User 정보 Map에 저장
                resultMap.put("auth_token", token);
                resultMap.put("user_id", loginUser.getUser_id());
                resultMap.put("user_name", loginUser.getUser_name());
                resultMap.put("user_email", loginUser.getUser_email());
                resultMap.put("user_nickName", loginUser.getUser_nickName());
                resultMap.put("user_tel", loginUser.getUser_tel());
                resultMap.put("user_birth", loginUser.getUser_birth());
                resultMap.put("user_type", loginUser.getUser_type());
                resultMap.put("user_createDate", loginUser.getUser_createDate());
                resultMap.put("user_profile", loginUser.getUser_profile());
                resultMap.put("user_about", loginUser.getUser_about());

                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", "로그인 실패");
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "정보(이름, 이메일)를 입력받아 사용자의 아이디를 반환한다.", response = String.class,
            notes = "findId(String user_name, String user_email)")
    @RequestMapping(value = "/findId", method = RequestMethod.POST)
    public ResponseEntity<String> findId(@RequestBody UserDto user) throws SQLException {
        String id = null;
        HttpStatus status = null;

        try {
            id = loginService.findId(user);

            if (id == null) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(id, status);
    }

    @ApiOperation(value = "정보(아이디, 이메일, 연락처)를 입력받아 사용자의 비밀번호를 사용자의 이메일로 전송한다.",
            response = NumberResult.class, notes = "findPassword(String user_id, String user_email, String user_tel)")
    @RequestMapping(value = "/findPassword", method = RequestMethod.POST)
    public ResponseEntity<NumberResult> findPassword(@RequestBody UserDto user) {
        HttpStatus status = null;
        NumberResult ns = new NumberResult();
        String password = null;

        try {
            password = loginService.findPassword(user);

            if (password != null) {

                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                // 메일 제목 설정
                helper.setSubject("비밀번호 안내 메일입니다.");
                // 수신자 설정
                helper.setTo(user.getUser_email());
                // 메일 내용
                String text = "블로그 사용자 비밀번호 안내 메일입니다. <br>";
                text += "사용자의 비밀번호는 [ " + password + " ] 입니다. <br><br>";
                text += "즐거운 사이트 이용 바랍니다. :)";
                helper.setText(text, true);

                // 메일 보내기
                javaMailSender.send(message);

                ns.setName("비밀번호가 이메일로 발송되었습니다.");
                ns.setCount(1);
                ns.setState("succ");

                status = HttpStatus.OK;
            } else {
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<NumberResult>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<NumberResult>(ns, status);
    }
}
