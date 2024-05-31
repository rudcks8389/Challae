package com.ezen.springmvc.domain.match.mapper;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.dto.MatchBoardDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CreateMapper {
    public List<MemberDto> findByClubNum(int clubNum);

    public List<FieldDto> findAllField();

    public void createMatch(CreateDto createDto);

    public void deleteMatchBoard(Map<String, Object> param);

    public void createMatchBoard(MatchBoardDto matchBoardDto);

    public MatchBoardDto loadMatchBoard(Map<String, Object> param);
}
