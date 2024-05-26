package com.ezen.springmvc;


import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.dto.FieldDto;
import com.ezen.springmvc.domain.match.mapper.CreateMapper;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class CreateMapperTest {

    @Autowired
    CreateMapper createMapper;

    @Test
    @DisplayName("구장 리스트 가져오기 테스트")
    void findAllFieldsTest() {
        List<FieldDto> list = createMapper.findAllField();
        for (FieldDto fieldDto : list) {
            log.info("구장 : {}", fieldDto);
        }
    }

    @Test
    @DisplayName("매치 생성 테스트")
    void createMatchTest() {
        CreateDto createDto = CreateDto.builder().matchDate("2024-02-12").matchInfo("하이").matchTime("11시").clubNum(1).fieldNum(1).matchPhoto("xx.jpeg").build();
        createMapper.createMatch(createDto);
        log.info("매치 등록 완료 : {}", createDto);
    }

    @Test
    @DisplayName("동일 클럽원 가져오기 테스트")
    void findByClubNumTest() {
        List<MemberDto> list = createMapper.findByClubNum(101);
        for (MemberDto memberDto : list) {
            log.info("동일 클럽원 목록 : {}", memberDto);
        }
    }
}








