package com.ezen.springmvc.domain.match.mapper;

import com.ezen.springmvc.domain.match.dto.CreateDto;


import com.ezen.springmvc.domain.match.dto.MemberListDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreateMapper {

    public List<MemberDto> findByClubNum(int clubNum);


    public void createMatch(CreateDto createDto);

    public List<MemberListDto> findByClub();
}

