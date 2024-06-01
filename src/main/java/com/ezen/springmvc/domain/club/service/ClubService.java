package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;

import java.util.List;

public interface ClubService {
    // 클럽 전체 목록 불러오기
    public List<ClubDto> clubList();


    // 클럽 생성하기
    public void clubRegister(ClubDto clubDto);

    public List<ClubDto> clubDataService(String clubNum);

    // 승인대기 클럽 목록 불러오기
    public List<ClubDto> findPendingClubs();

    // 관리자모드에서 클럽 신청 처리
    public void updateStatus(int clubNum, String status);

    // 관리자모드에서 클럽 신청 거절
    public void deleteClub(int clubNum);

    public int clubCount();

}
