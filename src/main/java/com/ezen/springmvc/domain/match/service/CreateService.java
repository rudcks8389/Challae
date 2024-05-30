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

    //myteam 경기일정 출력
    public  List<ClubMatchDto> getMatch(String clubNum);

    // 경기일정 상세청보
    public CreateDto getMachDetail (String matchNum);
}
