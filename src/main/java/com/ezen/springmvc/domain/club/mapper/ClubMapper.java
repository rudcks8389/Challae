package com.ezen.springmvc.domain.club.mapper;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {
    // 클럽 전체 목록 불러오기
    public List<ClubDto> clubList();

    // 클럽 생성하기
    public void clubRegister(ClubDto clubDto);
}
