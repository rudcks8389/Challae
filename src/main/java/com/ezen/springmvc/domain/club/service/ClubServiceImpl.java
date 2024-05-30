package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.mapper.ClubMapper;
import com.ezen.springmvc.domain.member.dto.MemberDto;
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


    // 클럽 번호 찾기
    @Override
    public ClubDto findByClubNum(String clubNum) {
        return clubMapper.findByClubNum(clubNum);
    }



    // 내 팀보기 클럽데이터 출력
    @Override
    public List<ClubDto> clubDataService(String clubNum) {
        return clubMapper.myClubData(clubNum);
    }



}
