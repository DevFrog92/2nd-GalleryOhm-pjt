package com.web.gallery.dto;

import lombok.Data;

/**** 카카오 로그인 토큰 DTO****/
@Data
public class OAuthTokenDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}
