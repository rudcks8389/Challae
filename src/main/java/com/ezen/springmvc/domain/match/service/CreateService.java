package com.ezen.springmvc.domain.match.service;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.ClubMatchDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;

import java.util.List;

public interface CreateService {

    public List<FieldDto> getFields();
    public void createMatch(CreateDto createDto);

    public List<MemberDto> findByClubNum(int clubNum);

    /**
     * myteam페이지 일정 랜더링 서비스
     * @param clubNum 클럽번호
     */
    public  List<ClubMatchDto> getMatch(String clubNum);

    /**
     * 경기 일정 상세정보 출력
     * @param matchNum 매치번호
     */
    public CreateDto getMachDetail (String matchNum);
}
