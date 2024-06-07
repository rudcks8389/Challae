package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.dto.ClubJoinListDto;
import com.ezen.springmvc.domain.common.dto.SearchDto;

import java.util.List;

public interface ClubService {
    /**
     * 클럽 전체 목록 불러오기
     *
     * @return 클럽 목록을 반환합니다.
     */
    public List<ClubDto> clubList();

    /**
     * 클럽 리스트 검색 클럽 목록 반환
     *
     * @param searchDto 검색 조건을 포함한 DTO
     * @return 검색 조건에 맞는 클럽 목록을 반환합니다.
     */
    public List<ClubDto> getClubList(SearchDto searchDto);

    /**
     * 클럽 리스트 검색 클럽 개수 반환
     *
     * @param searchDto 검색 조건을 포함한 DTO
     * @return 검색 조건에 맞는 클럽의 개수를 반환합니다.
     */
    public int getClubCount(SearchDto searchDto);

    /**
     * 클럽 번호로 클럽 찾기
     *
     * @param clubNum 클럽 번호
     * @return 해당 클럽 번호에 해당하는 클럽의 상세 정보를 반환합니다.
     */
    public ClubDto findByClubNum(String clubNum);

    /**
     * 클럽 생성하기
     *
     * @param clubDto 생성할 클럽의 정보를 포함한 DTO
     */
    public void clubRegister(ClubDto clubDto);

    /**
     * 클럽 가입 신청하기 (클럽장에게 가입 신청 보내기)
     *
     * @param clubJoinListDto 클럽 가입 신청 정보를 포함한 DTO
     */
    public void clubJoinRequest(ClubJoinListDto clubJoinListDto);

    /**
     * 클럽 가입 신청 보기 (클럽장이 가입 신청서 보기)
     *
     * @param clubNum 클럽 번호
     * @return 해당 클럽 번호에 해당하는 가입 신청서 목록을 반환합니다.
     */
    public List<ClubJoinListDto> clubJoinView(String clubNum);

    /**
     * 클럽 신청 승인 (상태 업데이트)
     *
     * @param joinNum 가입 신청 번호
     */
    public void clubJoinApprove(int joinNum);

    /**
     * 클럽 신청 거절 (상태 업데이트)
     *
     * @param joinNum 가입 신청 번호
     */
    public void clubJoinRefuse(int joinNum);

    public List<ClubDto> clubDataService(String clubNum);

    /**
     * 클럽 생성 중복 검사를 위한 클럽명 불러오기
     *
     * @param clubName 클럽명
     * @return 해당 클럽명을 가진 클럽의 정보를 반환합니다.
     */
    public ClubDto getClubName(String clubName);
}
