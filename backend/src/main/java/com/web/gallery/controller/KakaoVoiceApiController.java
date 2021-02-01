package com.web.gallery.controller;

import com.web.gallery.dto.TtsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/kakao")
@Api(value = "artGallery", description = "artGallery")
public class KakaoVoiceApiController {

    public static final Logger logger = LoggerFactory.getLogger(KakaoVoiceApiController.class);

    @ApiOperation(value = "[tts] 카카오 음성합성 byte 배열로 반환", response = byte[].class)
    @RequestMapping(value = "/makeVoice_byteArr", method = RequestMethod.POST)
    public ResponseEntity<byte[]> makeVoice_byteArr(@RequestBody TtsDto tts) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kakaoi-newtone-openapi.kakao.com/v1/synthesize";
        String xmlString = "<speak><voice name='WOMAN_READ_CALM'>" + tts.getContent() + "</voice></speak>";
        logger.info("xml : " + xmlString);
        byte[] bytes = xmlString.getBytes("utf-8");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.add("charset", "UTF-8");
        headers.add("Authorization", "KakaoAK f2491ac28aa5024dc22e01af6da42a5c");
        byte[] response = restTemplate.postForObject(url, new HttpEntity<byte []>(bytes, headers), byte[].class);

        return new ResponseEntity<byte[]>(response, HttpStatus.OK);
    }
}
