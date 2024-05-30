package com.ezen.springmvc.domain.match.mapper;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.ClubMatchDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.dto.MemberListDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CreateMapper {
    public List<MemberDto> findByClubNum(int clubNum);

    public List<FieldDto> findAllField();

    public void createMatch(CreateDto createDto);

    // myteam 경기일정 불러오기
    public List<ClubMatchDto> clubMatchByClubNum(String clubNum);

    // 경기 상세 일정
    public CreateDto clubDetailByMatchNum(String MatchNum);


}
