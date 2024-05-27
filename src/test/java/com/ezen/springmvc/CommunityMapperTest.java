package com.ezen.springmvc;

import com.ezen.springmvc.domain.community.dto.CommunityDto;
import com.ezen.springmvc.domain.community.mapper.CommunityMapper;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.mapper.MemberMapper;
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
    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("커뮤니티 출력 테스트")
//    @Disabled
    void findAllContentTest() {
        List<CommunityDto> content = communityMapper.getClubCommunity("101");
        for (CommunityDto communityDto : content) {
            log.info("comm content: {}", communityDto);
        }
    }

    @Test
    @DisplayName("커뮤니티 입력 테스트")
    void  inputCommunityTest() {
        CommunityDto testCreate = CommunityDto
                .builder()
                .commContent("Mapper @Test 양현종이 썼다라 가정")
                .clubNum("102")
                .memberNum("1007")
                .build();
        communityMapper.communityCreate(testCreate);
        log.info("커뮤니티 등록완료: {}", testCreate);

    }

}
