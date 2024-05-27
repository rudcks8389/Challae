package com.ezen.springmvc.domain.match.mapper;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.dto.MemberListDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreateMapper {
    public List<MemberDto> findByClubNum(int clubNum);

    public List<FieldDto> findAllField();

    public void createMatch(CreateDto createDto);

    // 매치 데이터를 가져 오기 위함
    public List<CreateDto> getClubNumMatch(String clubNum);
}
