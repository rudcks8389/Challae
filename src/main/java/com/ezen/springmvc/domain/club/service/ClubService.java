package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;

import java.util.List;

public interface ClubService {
    public List<ClubDto> clubList();
    public List<ClubDto> clubDataService(String clubNum);
}
