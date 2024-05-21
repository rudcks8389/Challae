package com.ezen.springmvc.domain.member.mapper;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.dto.MemberSearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Repository
@Mapper
public interface MemberMapper {
    public List<MemberDto> findByAll();
    public MemberDto findById(String id);
    public MemberDto findByIdAndPasswd(@Param("id") String id, @Param("passwd") String passwd);
    public List<MemberDto> findByAgeRange(@Param("begin") int begin, @Param("end") int end);
    public List<MemberDto> findByNameLike(String name);
    public void create(MemberDto member);
    public void update(MemberDto member);
    // 검색 타입별 회원 검색
    public List<MemberDto> findBySearchType(@Param("type") String type, @Param("value") String value);
    // 통합 검색
    public List<MemberDto> findBySearchAll(String value);
    // 통합 검색
    public List<MemberDto> findBySearchAllOption(MemberSearchCondition searchCondition);
}

