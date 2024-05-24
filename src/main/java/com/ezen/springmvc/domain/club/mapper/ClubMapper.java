package com.ezen.springmvc.domain.club.mapper;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {
    public List<ClubDto> clubList();

    // 내 팀보기 클럽데이터 출력용
    public List<ClubDto> myClubData(String clubNum);
}
