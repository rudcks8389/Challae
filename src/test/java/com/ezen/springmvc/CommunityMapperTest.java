package com.ezen.springmvc;

import com.ezen.springmvc.domain.community.dto.CommunityDto;
import com.ezen.springmvc.domain.community.mapper.CommunityMapper;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CommunityMapperTest {
    @Autowired
    CommunityMapper communityMapper;

    @Test
    @DisplayName("커뮤니티 출력 테스트")
//    @Disabled
    void findAllContentTest() {
        List<CommunityDto> content  = communityMapper.findAllContent();
        for (CommunityDto communityDto : content) {
            log.info("comm content: {}", communityDto);
        }
    }


}
