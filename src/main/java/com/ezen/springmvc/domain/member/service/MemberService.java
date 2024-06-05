package com.ezen.springmvc.domain.member.service;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 회원 관련 비즈니스 로직 처리 및 트랜잭션 관리
 */
public interface MemberService {

	/**
	 * 신규 회원가입
	 */
	public void register(MemberDto memberDto);

	/**
	 * 아이디와 비밀번호로 로그인하기
	 */
	public MemberDto isMember(String id, String passwd);

	/**
	 * 전체 회원 목록 반환
	 */
	public List<MemberDto> getMembers();

	/**
	 * 아이디로 회원정보 얻기
	 */
	public MemberDto getMember(String id);

	/**
	 * 회원정보 수정
	 */
	public void editMember(MemberDto member);

	/**
	 * 이름과 이메일로 아이디 찾기
	 */
	public MemberDto findId(String name , String email);

	/**
	 * 관리자 모드에서 전체 회원 수 보기
	 */
	public int memberCount();

	/**
	 * 관리자 모드에서 전체 회원 목록 보기
	 */
	public List<MemberDto> viewAllMember();

	/**
	 * 관리자 모드에서 회원 삭제
	 */
	public int deleteMember(int memberNum);

	/**
	 * 회원의 비밀번호 찾기
	 */
	public MemberDto findMemberByIdNameEmail(String memberId, String name, String email);

	/**
	 * 클럽 생성시 회원정보 수정
	 */
	public  void updateClub(int clubNum);

	// 내 팀보기
	public List<MemberDto> getTeamMember(String clubNum);
	/**
	 * 클럽 승인 후 감독여부 수정
	 */
	public void updateMemberWithClubInfo(int clubNum);

	/**
	 * 클럽 승인 후 Club_President에게 Member테이블 ClubNum부여
	 */
	public void updateClubNumByPresident(int clubNum);


}
