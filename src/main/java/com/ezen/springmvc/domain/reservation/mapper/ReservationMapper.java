package com.ezen.springmvc.domain.reservation.mapper;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    void insertReservation(ReservationDto reservationDto);
    public ReservationDto findByReservationNum(int resNum);
    public MemberDto findByMemberNum(int memberNum);

}
