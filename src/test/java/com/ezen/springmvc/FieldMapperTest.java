package com.ezen.springmvc;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.field.mapper.FieldMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
class FieldMapperTest {

    @Autowired
    FieldMapper fieldMapper;

    @Test
    @DisplayName("회원 전체 조회 테스트")
//    @Disabled
    void findAllTest() {
        List<FieldDto> list  = fieldMapper.findByAll();
        for (FieldDto fieldDto : list) {
            log.info("구장목록: {}", fieldDto);
        }
    }




}








