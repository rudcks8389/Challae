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

    /**
     * ClubNum 이용해서 클럽원 목록 가져오기
     * @param clubNum
     * @return
     */
    public List<MemberDto> findByClubNum(int clubNum);


    /**
     * 구장 전체 목록 가져오기
     * @return
     */
    public List<FieldDto> findAllField();


    /**
     * 새 경기 일정 생성
     * @param createDto
     */
    public void createMatch(CreateDto createDto);


    /**
     * clubNum 과 선택한 프리셋 타입을 사용해 기존에 있던 프리셋 삭제
     * @param param
     */
    public void deleteMatchBoard(Map<String, Object> param);


    /**
     * 새로운 프리셋 생성하기
     * @param matchBoardDto
     */
    public void createMatchBoard(MatchBoardDto matchBoardDto);


    /**
     * clubNum 과 선택한 프리셋 타입을 사용해 프리셋 불러오기
     * @param param
     * @return
     */
    public MatchBoardDto loadMatchBoard(Map<String, Object> param);
}
