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



}
