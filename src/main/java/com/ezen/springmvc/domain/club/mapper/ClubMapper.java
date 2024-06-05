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

    /**
     * /myteam 페이지 클럽데이터 출력
     * @param clubNum 클럽번호
     */
    public List<ClubDto> myClubData(String clubNum);

}
