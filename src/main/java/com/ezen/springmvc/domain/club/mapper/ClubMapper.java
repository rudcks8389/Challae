package com.ezen.springmvc.domain.club.mapper;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {
    // 클럽 전체 목록 불러오기
    public List<ClubDto> clubList();

    // 클럽 생성하기
    public void clubRegister(ClubDto clubDto);

    // 클럽번호 찾기
    public ClubDto findByClubNum(String clubNum);



    // 내 팀보기 클럽데이터 출력용
    public List<ClubDto> myClubData(String clubNum);

}
