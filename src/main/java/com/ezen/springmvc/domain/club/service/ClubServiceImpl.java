package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.mapper.ClubMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubServiceImpl implements ClubService {
    private final ClubMapper clubMapper;

    // 클럽 전체 목록 불러오기
    @Override
    public List<ClubDto> clubList(){
        return clubMapper.clubList();
    }

    // 클럽 생성하기
    @Override
    public void clubRegister(ClubDto clubDto) {
        clubMapper.clubRegister(clubDto);
    }

}
