package com.web.gallery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.gallery.dto.KakaoProfileDto;
import com.web.gallery.dto.OAuthTokenDto;
import com.web.gallery.dto.UserDto;
import com.web.gallery.service.JwtService;
import com.web.gallery.service.KaKaoAPI;
import com.web.gallery.service.LoginService;
import com.web.gallery.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/kakao")
@Api(value = "artGallery", description = "Art Gallery KaKao Login")
public class KakaoLoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private KaKaoAPI kakao;

    @Autowired
    private UserService userService;

    @Autowired
    private KaKaoAPI kakaoService;

    @Autowired
    private LoginService loginService;

    public static final Logger logger = LoggerFactory.getLogger(KakaoLoginController.class);


    @ApiOperation(value = "카카오 로그인", response = String.class)
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public ResponseEntity<String> kakaoLogin(@RequestParam("code") String code) throws Exception{ // Data를 리턴해주는 컨트롤러 함수
        // POST 방식으로 key=value 데이터를 요청(카카오 쪽으로, Token받기 위해)
        // Retrofit2
        // OkHTTP
        // RestTemplate
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "f2491ac28aa5024dc22e01af6da42a5c");
        params.add("redirect_uri", "http://localhost:7080/artGallery/api/kakao/callback");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        // Http 요청하기 - Poslt방식으로 - 그리고 response 변수의 응답을 받음.
        ResponseEntity response = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        String token = kakaoService.getAccessToken(code);
        System.out.println("카카오 토큰 요청 완료 : 토큰요청에 대한 응답1 : " + token);

        // Gson, Json Simple, ObjectMapper 라이브러리
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenDto oauthToken = null;
        try {
            oauthToken = objectMapper.readValue((String) response.getBody(),OAuthTokenDto.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        System.out.println("카카오 로그인 코드값 : " + code);
        System.out.println("카카오 토큰 요청 완료 : 토큰요청에 대한 응답 : " + response);
        System.out.println("카카오 액세스 토큰 : " + oauthToken.getAccess_token());



        /**** 카카오로 회원가입 ****/
        KakaoProfileDto kakaoProfileDto = kakao.getUser(oauthToken.getAccess_token());
        /**** user DB에 넣을 값 (아이디, 이름, 이메일, 비밀번호) ****/
        String user_id = kakaoProfileDto.getKakao_account().getEmail().split("@")[0];
        if(user_id.length()>10) user_id = user_id.substring(0,10);
        user_id = user_id+kakaoProfileDto.getId(); // 아이디 값
        String user_name = kakaoProfileDto.getKakao_account().profile.getNickname();
        String user_email = kakaoProfileDto.getKakao_account().getEmail();
        UUID garbagePassword = UUID.randomUUID();
        String user_password = garbagePassword.toString().split("-")[0];
        // 가입자와 비가입자 체크
        if(userService.checkId(user_id) == 0){ // 해당 카카오 아이디로 가입한 적 없는 사람만 가입.
            UserDto user = new UserDto();
            user.setUser_id(user_id);
            user.setUser_name(user_name);
            user.setUser_password(user_password);
            user.setUser_email(user_email);
            user.setUser_tel("01000000000");

            userService.joinUser(user);
        } else{
            System.out.println("이미 가입 되어있는 회원입니다.");
        }

        // 로그인
        /*
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
                String loginToken = jwtService.create(loginUser);
                logger.trace("로그인 토큰정보 : {}", loginToken);

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
        }*/
        return new ResponseEntity<String>(kakaoProfileDto.toString(), HttpStatus.OK);
    }

}