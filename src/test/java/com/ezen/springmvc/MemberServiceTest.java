package com.ezen.springmvc;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ezen.springmvc.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@SpringBootTest
@Slf4j
public class MemberServiceTest {
	
	@Autowired
	private MemberService memberService;

	@Test
	@DisplayName("회원 전체 조회 테스트")
//	@Disabled
	void getMembersTest(){
		List<MemberDto> list =  memberService.getMembers();
		log.info("회원 전체 목록 : {}", list);
	}
	
	@Test
	@DisplayName("회원 인증 테스트")
	@Disabled
	void isMemberTest() {
		String id = "bangry", passwd = "1111";
		MemberDto isMember = memberService.isMember(id, passwd);
		log.info("인증 사용자 정보 : {}", isMember);
	}
}







