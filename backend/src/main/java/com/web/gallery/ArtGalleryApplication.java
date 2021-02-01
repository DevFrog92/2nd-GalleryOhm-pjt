package com.web.gallery;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.web.gallery.interceptor.JwtInterceptor;;

@SpringBootApplication
@EnableTransactionManagement
public class ArtGalleryApplication implements WebMvcConfigurer {

	// 실행 -> 레스트풀 메인 시작
	public static void main(String[] args) {
		SpringApplication.run(ArtGalleryApplication.class, args);
	}

	@Autowired
    private JwtInterceptor jwtInterceptor;

	// JWTInterceptor를 설치한다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/**") 			// 기본 적용 경로
                .excludePathPatterns(Arrays.asList("/user/confirm/**", ""));	// 적용 제외 경로
    }

    // Interceptor를 이용해서 처리하므로 전역의 Cross Origin 처리를 해준다.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("auth_token");
    }
}

// http://localhost:7080/artGallery/swagger-ui.html
