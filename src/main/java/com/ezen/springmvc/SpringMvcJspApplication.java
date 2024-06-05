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
    }
}
