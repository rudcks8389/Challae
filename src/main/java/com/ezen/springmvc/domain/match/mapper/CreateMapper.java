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

    /**
     * myteam 페이지 일정 랜더링
     * @param clubNum 클럽번호
     */
    public List<ClubMatchDto> clubMatchByClubNum(String clubNum);

    /**
     * 경기 상세일정 정보 출력
     * @param MatchNum 매치번호
     */
    public CreateDto clubDetailByMatchNum(String MatchNum);


}
