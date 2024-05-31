package com.ezen.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * upload 폴더에 있는 이미지에 접근하는 핸들러
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/preset/**")  // upload/preset 으로 들어오는 요청들은 ↓ 밑의 주소로 ..
                .addResourceLocations("file:/Applications/ezen-fullstack/workspace/Project-CHALLAE/src/main/upload/presetboard/");

        registry.addResourceHandler("/upload/profile/**")
                .addResourceLocations("file:/Applications/ezen-fullstack/workspace/Project-CHALLAE/src/main/upload/profile/");
    }

}
