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
    @DisplayName("커뮤니티 출력 테스트")
//	@Disabled
    void getCommTest(){
        List<CommunityDto> comm =  communityService.getCommunityContents("101");
        log.info("comm Content : {}", comm);
    }

<<<<<<< HEAD
//    @Test
//    @DisplayName("커뮤니티 입력 저장테스트")
//    void inputCommTest(){
//        CommunityDto communityDto =
//    }
=======

>>>>>>> b4ab504d41e01dc4860cddc8d2390e6ee8f8847a
}
