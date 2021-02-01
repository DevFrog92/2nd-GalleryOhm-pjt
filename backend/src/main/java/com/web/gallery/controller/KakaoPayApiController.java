package com.web.gallery.controller;

import com.web.gallery.dto.KakaopayApprovalDto;
import com.web.gallery.dto.KakaopayDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/kakaopay")
@Api(value = "artGallery", description = "artGallery")
public class KakaoPayApiController {
    public static final Logger logger = LoggerFactory.getLogger(KakaoPayApiController.class);

    private static final String HOST = "https://kapi.kakao.com";

    private KakaopayDto kakaopayDto;
    private KakaopayApprovalDto kakaopayApprovalDto;

    @ApiOperation(value = "카카오페이 연동 테스트", response = String.class)
    @RequestMapping(value = "/payRequest", method = RequestMethod.POST)
    public ResponseEntity<String> payRequest() {
        HttpStatus status = null;

        RestTemplate restTemplate = new RestTemplate();

        // 요청 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","KakaoAK "+"4189c284bd2768e7bed7ef9ae5e30cd3");
        headers.add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type",MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        // 요청 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid","TC0ONETIME");
        params.add("partner_order_id","order01");
        params.add("partner_user_id","qhddbwjd");
        params.add("item_name","테스트결제");
        params.add("quantity","1");
        params.add("total_amount","2000");
        params.add("tax_free_amount","100");
        params.add("approval_url","http://localhost:8080/kakaopay_approval");
        params.add("fail_url","http://localhost:8080/kakaopay_fail");
        params.add("cancel_url","http://localhost:8080/kakaopay_cancle");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String,String>>(params,headers);

        try {

            kakaopayDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body,KakaopayDto.class);

            logger.info(""+kakaopayDto);
            status = HttpStatus.OK;

        }catch (RestClientException e){
            logger.info(e+"");
            status = HttpStatus.BAD_REQUEST;
        }catch (Exception e){
            logger.info(e+"");
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<String>(kakaopayDto.getNext_redirect_pc_url(), status);
    }

    @ApiOperation(value = "카카오페이 승인테스트", response = KakaopayApprovalDto.class)
    @RequestMapping(value = "/payApproval/{pg_token}", method = RequestMethod.GET)
    public ResponseEntity<KakaopayApprovalDto> payApproval(@PathVariable("pg_token") String pg_token) {
        HttpStatus status = null;

        RestTemplate restTemplate = new RestTemplate();

        // 요청 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","KakaoAK "+"4189c284bd2768e7bed7ef9ae5e30cd3");
        headers.add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type",MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        // 요청 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid","TC0ONETIME");
        params.add("tid",kakaopayDto.getTid());
        params.add("partner_order_id","order01");
        params.add("partner_user_id","qhddbwjd");
        params.add("pg_token",pg_token);
        params.add("total_amount","2000");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String,String>>(params,headers);

        try {

            kakaopayApprovalDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,KakaopayApprovalDto.class);

            logger.info(""+kakaopayApprovalDto);
            status = HttpStatus.OK;

        }catch (RestClientException e){
            logger.info(e+"");
            status = HttpStatus.BAD_REQUEST;
        }catch (Exception e){
            logger.info(e+"");
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<KakaopayApprovalDto>(kakaopayApprovalDto, status);
    }
}
