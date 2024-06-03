package com.ezen.springmvc.domain.match.service;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.dto.MatchBoardDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;

import java.util.List;

public interface CreateService {

    /**
     * 구장 전체 목록 가져오기
     * @return 구장목록 리스트
     */
    public List<FieldDto> getFields();


    /**
     * 새 경기 일정 생성
     * @param createDto
     */
    public void createMatch(CreateDto createDto);


    /**
     * ClubNum 이용해서 클럽원 목록 가져오기
     * @param clubNum
     * @return 해당 클럽원 전체 목록리스트
     */
    public List<MemberDto> findByClubNum(int clubNum);

    /**
     * clubNum 과 선택한 프리셋 타입을 사용해 기존에 있던 프리셋 삭제
     * @param clubNum
     * @param type
     */
    public void deleteMatchBoard(int clubNum, String type);

    /**
     * 새로운 프리셋 생성하기
     * @param matchBoardDto
     */
    public void createMatchBoard(MatchBoardDto matchBoardDto);

    /**
     * clubNum 과 선택한 프리셋 타입을 사용해 프리셋 불러오기
     * @param clubNum
     * @param type
     * @return 선택한 조건에 맞는 프리셋 반환
     */
    public MatchBoardDto loadMatchBoard(int clubNum, String type); // clubNum 만 서버에서 받아 쓰기.

}
