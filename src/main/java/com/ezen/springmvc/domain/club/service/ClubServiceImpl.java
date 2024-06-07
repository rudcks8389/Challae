package com.ezen.springmvc.domain.club.service;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.dto.ClubJoinListDto;
import com.ezen.springmvc.domain.club.mapper.ClubMapper;
import com.ezen.springmvc.domain.common.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ClubServiceImpl implements ClubService {
    private final ClubMapper clubMapper;

    /**
     * 클럽 전체 목록 불러오기
     *
     * @return 클럽 목록을 반환합니다.
     */
    @Override
    public List<ClubDto> clubList() {
        return clubMapper.clubList();
    }

    /**
     * 클럽 리스트 검색 클럽 목록 반환
     *
     * @param searchDto 검색 조건을 포함한 DTO
     * @return 검색 조건에 맞는 클럽 목록을 반환합니다.
     */
    @Override
    public List<ClubDto> getClubList(SearchDto searchDto) {
        return clubMapper.findBySearchCondition(searchDto);
    }

    /**
     * 클럽 리스트 검색 클럽 개수 반환
     *
     * @param searchDto 검색 조건을 포함한 DTO
     * @return 검색 조건에 맞는 클럽의 개수를 반환합니다.
     */
    @Override
    public int getClubCount(SearchDto searchDto) {
        return clubMapper.countBySearchCondition(searchDto);
    }

    /**
     * 클럽 생성하기
     *
     * @param clubDto 생성할 클럽의 정보를 포함한 DTO
     */
    @Override
    public void clubRegister(ClubDto clubDto) {
        clubMapper.clubRegister(clubDto);
    }

    /**
     * 클럽 정보 제공 서비스
     *
     * @param clubNum 클럽번호
     * @return
     */
    @Override
    public List<ClubDto> clubDataService(String clubNum) {
        return clubMapper.myClubData(clubNum);
    }


    /**
     * 클럽 번호로 클럽 찾기
     *
     * @param clubNum 클럽 번호
     * @return 해당 클럽 번호에 해당하는 클럽의 상세 정보를 반환합니다.
     */
    @Override
    public ClubDto findByClubNum(String clubNum) {
        return clubMapper.findByClubNum(clubNum);
    }

    /**
     * 클럽 가입 신청하기 (클럽장에게 가입 신청 보내기)
     *
     * @param clubJoinListDto 클럽 가입 신청 정보를 포함한 DTO
     */
    @Override
    public void clubJoinRequest(ClubJoinListDto clubJoinListDto) {
        clubMapper.clubJoinRequest(clubJoinListDto);
    }

    /**
     * 클럽 가입 신청 보기 (클럽장이 가입 신청서 보기)
     *
     * @param clubNum 클럽 번호
     * @return 해당 클럽 번호에 해당하는 가입 신청서 목록을 반환합니다.
     */
    @Override
    public List<ClubJoinListDto> clubJoinView(String clubNum) {
        return clubMapper.clubJoinView(clubNum);
    }

    /**
     * 클럽 신청 승인 (상태 업데이트)
     *
     * @param joinNum 가입 신청 번호
     */
    @Override
    public void clubJoinApprove(int joinNum) {
        clubMapper.clubJoinApprove(joinNum);
    }

    /**
     * 클럽 신청 거절 (상태 업데이트)
     *
     * @param joinNum 가입 신청 번호
     */
    @Override
    public void clubJoinRefuse(int joinNum) {
        clubMapper.clubJoinRefuse(joinNum);
    }

    /**
     * 관리자모드에서 승인대기 클럽 출력
     */
    @Override
    public List<ClubDto> findPendingClubs() {
        return clubMapper.findPendingClubs();
    }

    /**
     * 관리자모드에서 클럽 생성 승인
     */
    @Override
    public void updateStatus(int clubNum, String status) {
        clubMapper.updateStatus(clubNum, status);
    }

    /**
     * 관리자모드에서 클럽 생성 거절
     */
    @Override
    public void deleteClub(int clubNum) {
        clubMapper.deleteClub(clubNum);
    }

    /**
     * 관리자모드에서 보는 클럽 수 출력
     */
    @Override
    public int clubCount() {
        return clubMapper.clubCount();
    }

    /**
     * 관리자모드에서 이메일 발송을 위한 아이디 찾기
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

    /**
     * 클럽 생성 중복 검사를 위한 클럽명 불러오기
     *
     * @param clubName 클럽명
     * @return 해당 클럽명을 가진 클럽의 정보를 반환합니다.
     */
    @Override
    public ClubDto getClubName(String clubName) {
        return clubMapper.getClubName(clubName);
    }

}
