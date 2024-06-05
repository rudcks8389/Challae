package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.mapper.ClubMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ClubServiceImpl implements ClubService {
    private final ClubMapper clubMapper;

    // 클럽 전체 목록 불러오기
    @Override
    public List<ClubDto> clubList(){
        return clubMapper.clubList();
    }


    // 클럽 생성하기
    @Override
    public void clubRegister(ClubDto clubDto) {
        clubMapper.clubRegister(clubDto);
    }

    /**
     * 클럽 정보 제공 서비스
     * @param clubNum 클럽번호
     * @return
     */
    @Override
    public List<ClubDto> clubDataService(String clubNum) {
        return clubMapper.myClubData(clubNum);
    }

    /**
     *  관리자모드에서 승인대기 클럽 출력
     */
    @Override
    public List<ClubDto> findPendingClubs() {
        return clubMapper.findPendingClubs();
    }

    /**
     *  관리자모드에서 클럽 생성 승인
     */
    @Override
    public void updateStatus(int clubNum, String status) {
        clubMapper.updateStatus(clubNum, status);
    }

    /**
     *  관리자모드에서 클럽 생성 거절
     */
    @Override
    public void deleteClub(int clubNum) {
        clubMapper.deleteClub(clubNum);
    }

    /**
     *  관리자모드에서 보는 클럽 수 출력
     */
    @Override
    public int clubCount() {
        return clubMapper.clubCount();
    }

    /**
     *  관리자모드에서 이메일 발송을 위한 아이디 찾기
     */
    @Override
    public String findClubById(int clubNum) {
        return clubMapper.findClubById(clubNum);
    }

    /**
     * 관리자 모드에서 전체 클럽 목록 보기
     */
    public List<ClubDto> allClubView() {
        return clubMapper.allClubView();
    }

}
