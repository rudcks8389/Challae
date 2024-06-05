package com.ezen.springmvc.domain.reservation.service;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
/**
 * RerservationService
 */
public interface ReservationService {

    /**
     * 예약 저장 메서드 추가
     * @param reservationDto
     */
    public void reserveField(ReservationDto reservationDto);

    /**
     * 예약번호로 값불러오기
     * @param resNum
     * @return
     */
    public ReservationDto findByReservationNum(int resNum);

    /**
     * 회원번호로 값불러오기
     * @param memberNum
     * @return
     */
    public MemberDto findByMemberNum(int memberNum);
}
