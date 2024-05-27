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

    @Test
    @DisplayName("클럽 매치일정 조회 서비스 테스트")
    void getClubMatchServiceTest(){
        List<CreateDto> match = createService.getClubMatch("101");
        log.info("매치 서비스 리스트 출력 : {}",match);
    }



}

