package com.ezen.springmvc.domain.match.mapper;

import com.ezen.springmvc.domain.match.dto.MemberListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreateMapper {
    public List<MemberListDto> findByClub();
}
