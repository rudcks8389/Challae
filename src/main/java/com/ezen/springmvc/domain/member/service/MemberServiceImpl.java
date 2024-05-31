package com.ezen.springmvc.domain.member.service;

import com.ezen.springmvc.domain.club.dto.SearchDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ezen.springmvc.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;
	
	@Override
	@Transactional
	public void register(MemberDto memberDto) {
		memberMapper.create(memberDto);
	}

	@Override
	public MemberDto isMember(String id, String passwd) {
		return memberMapper.findByIdAndPasswd(id, passwd);
	}

	@Override
	public List<MemberDto> getMembers() {
		return memberMapper.findByAll();
	}

	@Override
	public MemberDto getMember(String id) {
		return memberMapper.findById(id);
	}

	@Override
	@Transactional
	public void editMember(MemberDto memberDto) {
		memberMapper.update(memberDto);
	}



	/**
	 * myTeam 페이지를 위한 Service 구현
	 * @param clubNum
	 * @param searchDto
	 * @return
	 */

	// 팀원 목록 출력
	@Override
	public List<MemberDto> getTeamMember(String clubNum, SearchDto searchDto) {
		return memberMapper.myTeamList(clubNum,searchDto);
	}

	// 팀원 목록 카운팅
	@Override
	public int getTeamMemberCount(String clubNum, SearchDto searchDto) {
		return memberMapper.countMyTeamList(clubNum,searchDto);
	}
	// 강퇴 기능
	@Override
	public void outClubMember(MemberDto memberDto) {
		memberMapper.ClubMemberDelete(memberDto);
	}


}
