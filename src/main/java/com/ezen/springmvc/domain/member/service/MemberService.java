package com.ezen.springmvc.domain.member.service;

import com.ezen.springmvc.domain.club.dto.SearchDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;

import java.lang.reflect.Member;
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

	/**
	 * 클럽원 출력(조회) 서비스
	 * @param clubNum 클럽번호
	 * @param searchDto 검색 Dto
	 */
	public List<MemberDto> getTeamMember(String clubNum, SearchDto searchDto);

	/**
	 * 팀원 목록 카운팅 서비스 (for pagination)
	 * @param clubNum 클럽번호
	 * @param searchDto 검색Dto
	 */
	public  int getTeamMemberCount (String clubNum, SearchDto searchDto);

	/**
	 * 팀원 팀강퇴서비스
	 * @param memberDto 회원Dto
	 */
	public void outClubMember (MemberDto memberDto);
}
