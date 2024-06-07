package com.ezen.springmvc.domain.club.mapper;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.dto.ClubJoinListDto;
import com.ezen.springmvc.domain.common.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClubMapper {
    /**
     * 클럽 전체 목록 불러오기
     *
     * @return 클럽 목록을 반환합니다.
     */
    public List<ClubDto> clubList();

    /**
     * 클럽 리스트 검색 목록 반환
     *
     * @param searchDto 검색 조건을 포함한 DTO
     * @return 검색 조건에 맞는 클럽 목록을 반환합니다.
     */
    public List<ClubDto> findBySearchCondition(@Param("searchDto") SearchDto searchDto);

    /**
     * 클럽 리스트 검색 개수 반환
     *
     * @param searchDto 검색 조건을 포함한 DTO
     * @return 검색 조건에 맞는 클럽의 개수를 반환합니다.
     */
    public int countBySearchCondition(@Param("searchDto") SearchDto searchDto);

    /**
     * 클럽 생성
     *
     * @param clubDto 생성할 클럽의 정보를 포함한 DTO
     */
    public void clubRegister(ClubDto clubDto);

    /**
<<<<<<< HEAD
     * /myteam 페이지 클럽데이터 출력
     * @param clubNum 클럽번호
     */
    public List<ClubDto> myClubData(String clubNum);

    /**
     *  관리자모드에서 승인대기 클럽 출력
     */
    public List<ClubDto> findPendingClubs();

    /**
     *  관리자모드에서 보는 클럽 수 출력
     */

    public int clubCount();

    /**
     *  관리자모드에서 클럽 생성 승인
     */
    public void updateStatus(int clubNum, String status);

    /**
     *  관리자모드에서 클럽 생성 거절
     */
    public void deleteClub(int clubNum);

    /**
     *  관리자모드에서 이메일 발송을 위한 아이디 찾기
     */
    public String findClubById(int clubNum);

    /**
     * 관리자 모드에서 전체 클럽 목록 보기
     */
    public List<ClubDto> allClubView();



=======
     * 클럽 번호로 클럽 상세보기
     *
     * @param clubNum 클럽 번호
     * @return 해당 클럽 번호에 해당하는 클럽의 상세 정보를 반환합니다.
     */
    public ClubDto findByClubNum(String clubNum);

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

    // 내 팀보기 클럽데이터 출력용
    public List<ClubDto> myClubData(String clubNum);

    /**
     * 클럽 생성 중복 검사를 위한 클럽명 불러오기
     *
     * @param clubName 클럽명
     * @return 해당 클럽명을 가진 클럽의 정보를 반환합니다.
     */
    public ClubDto getClubName(String clubName);
>>>>>>> 1776326671b77b631dcaf6d8dc377d02609568b6
}
