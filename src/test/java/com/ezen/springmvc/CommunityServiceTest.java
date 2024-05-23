package com.ezen.springmvc;

import com.ezen.springmvc.domain.community.dto.CommunityDto;
import com.ezen.springmvc.domain.community.service.CommunityService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CommunityServiceTest {

    @Autowired
    CommunityService communityService;

    @Test
    @DisplayName("회원 전체 조회 테스트")
//	@Disabled
    void getCommTest(){
        List<CommunityDto> comm =  communityService.getContents();
        log.info("comm Content : {}", comm);
    }
}
