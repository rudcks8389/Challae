package com.ezen.springmvc;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.field.service.FieldService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FieldServiceTest {
	
	@Autowired
	private FieldService fieldService;


	@Test
	@DisplayName("구장 전체 조회 테스트")
//	@Disabled
	void getFieldsTest(){
		List<FieldDto> list =  fieldService.findByAll();
		log.info("구장 전체 목록 : {}", list);
	}
	

}







