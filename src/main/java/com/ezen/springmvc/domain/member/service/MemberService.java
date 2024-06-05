package com.ezen.springmvc.domain.member.service;

import com.ezen.springmvc.domain.common.dto.SearchDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Member;
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
<<<<<<< HEAD
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


	/**
	 * 클럽 승인 후 감독여부 수정
	 */
	public void updateMemberWithClubInfo(int clubNum);

	/**
	 * 클럽 승인 후 Club_President에게 Member테이블 ClubNum부여
	 */
	public void updateClubNumByPresident(int clubNum);

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
