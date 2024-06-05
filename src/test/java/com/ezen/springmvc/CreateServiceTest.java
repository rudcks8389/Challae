package com.ezen.springmvc;

import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
@Setter
public class CreateServiceTest {
    @Autowired
    CreateService createService;

//    @Test
//    public void match(){
//        List<CreateDto> match = createService.getMatch(101,"24-05");
//        for (CreateDto createDto : match) {
//            log.info("101 클럽 경기일정 : {}",createDto);
//        }
//    }




}

