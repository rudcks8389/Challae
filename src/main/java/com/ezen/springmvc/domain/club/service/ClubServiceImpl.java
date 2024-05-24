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

    @Override
    public List<ClubDto> clubList(){
        return clubMapper.clubList();
    }

    // 내 팀보기 클럽데이터 출력
    @Override
    public List<ClubDto> clubDataService(String clubNum) {
        return clubMapper.myClubData(clubNum);
    }
}
