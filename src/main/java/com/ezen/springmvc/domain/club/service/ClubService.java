package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;

import java.util.List;

public interface ClubService {
    // 클럽 전체 목록 불러오기
    public List<ClubDto> clubList();
<<<<<<< HEAD

    // 클럽 생성하기
    public void clubRegister(ClubDto clubDto);
=======
    public List<ClubDto> clubDataService(String clubNum);
>>>>>>> main
}
