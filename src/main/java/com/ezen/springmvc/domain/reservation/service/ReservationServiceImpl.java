package com.ezen.springmvc.domain.reservation.service;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
import com.ezen.springmvc.domain.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationMapper reservationMapper;

    @Override
    @Transactional
    public void reserveField(ReservationDto reservationDto) {
        reservationMapper.insertReservation(reservationDto);
    }

    @Override
    public ReservationDto findByReservationNum(int resNum) {
        return reservationMapper.findByReservationNum(resNum);
    }

    @Override
    public MemberDto findByMemberNum(int memberNum) {
        return reservationMapper.findByMemberNum(memberNum);
    }
}
