package com.ezen.springmvc.domain.reservation.service;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.reservation.dto.ReservationDto;

public interface ReservationService {

    public void reserveField(ReservationDto reservationDto); // 예약 저장 메서드 추가
    public ReservationDto findByReservationNum(int resNum);
    public MemberDto findByMemberNum(int memberNum);
}
