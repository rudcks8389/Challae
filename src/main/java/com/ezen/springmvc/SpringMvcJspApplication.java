package com.ezen.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class SpringMvcJspApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringMvcJspApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext  = SpringApplication.run(SpringMvcJspApplication.class, args);
        int count = applicationContext.getBeanDefinitionCount();
        log.info("=================================================");
        log.info("스프링 컨테이너에 등록된 빈의 갯수 : {}", count);
        log.info("=================================================");

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        log.info("=================================================");
        log.info("등록된 빈 목록");
        Arrays.asList(beanNames).forEach(beanName -> {
            log.info("빈이름 : {}", beanName);
        });
        log.info("=================================================");

    }

}
