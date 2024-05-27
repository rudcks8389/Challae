package com.ezen.springmvc;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.mapper.ClubMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ClubMapperTest {
    @Autowired
    ClubMapper clubMapper;

    @Test
    @DisplayName("내 팀 정보 출력 테스트")
    void myClubInfoTest(){
        List<ClubDto> clubInfo = clubMapper.myClubData("101");
        log.info("내 팀 정보 : {}",clubInfo);
    }

}
