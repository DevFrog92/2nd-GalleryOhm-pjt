package com.web.gallery.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.gallery.dto.KakaoProfileDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KaKaoAPI {

    /**** 카카오 로그인 코드로 토큰값 가져오기 ****/
    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=f2491ac28aa5024dc22e01af6da42a5c");
            sb.append("&redirect_uri=http://localhost:7080/artGallery/api/kakaologin/kakaoLogin");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return access_Token;
    }

    /**** 카카오 로그인 Token으로 유저 정보 반환 ****/
    public KakaoProfileDto getUser(String access_Token){
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+ access_Token);
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");


        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        // Http 요청하기 - Poslt방식으로 - 그리고 response 변수의 응답을 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        System.out.println("카카오 프로필 정보 Json" + response.getBody());

        // Gson, Json Simple, ObjectMapper 라이브러리
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfileDto kakaoProfileDto = null;
        try {
            kakaoProfileDto = objectMapper.readValue((String) response.getBody(), KakaoProfileDto.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        // User 오브젝트 : user_name, user_id, user_password, user_email
        System.out.println("카카오 아이디(번호) : " + kakaoProfileDto.getId());
        System.out.println("카카오 이메일 : " + kakaoProfileDto.getKakao_account().getEmail());

        String user_id = kakaoProfileDto.getKakao_account().getEmail().split("@")[0];
        if(user_id.length()>10) user_id = user_id.substring(0,10);
        user_id = user_id+kakaoProfileDto.getId();
        String user_name = kakaoProfileDto.getKakao_account().profile.getNickname();
        String user_email = kakaoProfileDto.getKakao_account().getEmail();
        UUID garbagePassword = UUID.randomUUID();
        System.out.println("카카오 유저 아이디 : " + user_id);
        System.out.println("카카오 유저 이름 : " + user_name);
        System.out.println("카카오 유저 이메일 : " + user_email);
        System.out.println("카카오 유저 비밀번호 : " + garbagePassword.toString().split("-")[0]);

        return kakaoProfileDto;
    }
}
