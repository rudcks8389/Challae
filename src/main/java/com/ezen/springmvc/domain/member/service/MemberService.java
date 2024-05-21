package com.ezen.springmvc.domain.member.service;

import com.ezen.springmvc.domain.member.dto.MemberDto;

import java.util.List;

/**
 * 회원 관련 비즈니스 로직 처리 및 트랜잭션 관리
 */
public interface MemberService {
	public void register(MemberDto memberDto);
	public MemberDto isMember(String id, String passwd);
	public List<MemberDto> getMembers();
	public MemberDto getMember(String id);
	public void editMember(MemberDto member);
}
