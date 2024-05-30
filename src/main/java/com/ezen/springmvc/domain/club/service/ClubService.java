package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;

import java.util.List;

public interface ClubService {
    // 클럽 전체 목록 불러오기
    public List<ClubDto> clubList();

    // 클럽 번호 찾기
    public ClubDto findByClubNum(String clubNum);

    // 클럽 생성하기
    public void clubRegister(ClubDto clubDto);



    public List<ClubDto> clubDataService(String clubNum);


}
