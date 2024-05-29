package com.ezen.springmvc.web.common.config;

import com.ezen.springmvc.web.common.intercepter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/** 인터셉터 등록을 위한 자바 설정 클래스 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 로그인 체크가 필요 없는 요청 URI 목록
    public static final  List<String> loginNotEssential =
            Arrays.asList(
                    "/", "/css/**", "/img/**", "/js/**", "/*.ico", "/vendor/**","/video/**",
                    "/member/signup/**", "/member/idcheck/{inputId}", "/member/image/{profileFileName}",
                    "/member/signin", "/member/signout",
                    "/board/list/**","/board/article","/field/list","/club/list","/member/find","/member/find/result","/error");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 로그인 체크 인터셉터 등록
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(loginNotEssential);
    }
}





