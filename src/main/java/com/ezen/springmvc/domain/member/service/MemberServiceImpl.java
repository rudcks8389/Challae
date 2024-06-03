package com.ezen.springmvc.domain.member.service;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ezen.springmvc.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;


/**
 * MemeberService 구현체 */
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;

	/**
	 * 신규 회원가입
	 */
	@Override
	@Transactional
	public void register(MemberDto memberDto) {
		memberMapper.create(memberDto);
	}

	/**
	 * 아이디와 비밀번호로 로그인하기
	 */
	@Override
	public MemberDto isMember(String id, String passwd) {
		return memberMapper.findByIdAndPasswd(id, passwd);
	}

	/**
	 * 전체 회원 목록 반환
	 */
	@Override
	public List<MemberDto> getMembers() {
		return memberMapper.findByAll();
	}

	/**
	 * 아이디로 회원정보 얻기
	 */
	@Override
	public MemberDto getMember(String id) {
		return memberMapper.findById(id);
	}

	/**
	 * 회원정보 수정
	 */
	@Override
	@Transactional
	public void editMember(MemberDto memberDto) {
		memberMapper.update(memberDto);
	}

	/**
	 * 이름과 이메일로 아이디 찾기
	 */
	@Override
	public MemberDto findId(String name , String email) {
		return memberMapper.findId(name,email);
	}

	/**
	 * 관리자 모드에서 전체 회원 수 보기
	 */
	@Override
	public int memberCount() {
		return memberMapper.memberCount();
	}

	/**
	 * 관리자 모드에서 전체 회원 목록 보기
	 */
	@Override
	public List<MemberDto> viewAllMember() {
		return memberMapper.viewAllMember();
	}

	/**
	 * 관리자 모드에서 회원 삭제
	 */
	@Override
	public int deleteMember(int memberNum) {
		return memberMapper.deleteMember(memberNum);
	}

	/**
	 * 회원의 비밀번호 찾기
	 */
	@Override
	public MemberDto findMemberByIdNameEmail(String memberId, String name, String email) {
		return memberMapper.findMemberByIdNameEmail(memberId,name,email);
	}

	@Override
	public List<MemberDto> getTeamMember(String clubNum) {
		return memberMapper.myTeamList(clubNum);
	}


}
