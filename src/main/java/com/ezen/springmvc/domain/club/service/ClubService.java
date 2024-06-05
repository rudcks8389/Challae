package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;

import java.util.List;

public interface ClubService {
    // 클럽 전체 목록 불러오기
    public List<ClubDto> clubList();


    // 클럽 생성하기
    public void clubRegister(ClubDto clubDto);

    /**
     * 클럽 데이터 제공서비스
     * @param clubNum 클럽번호
     * @return
     */
    public List<ClubDto> clubDataService(String clubNum);

}
