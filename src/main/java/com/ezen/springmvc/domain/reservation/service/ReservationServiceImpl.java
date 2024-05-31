package com.ezen.springmvc.domain.reservation.service;

import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
import com.ezen.springmvc.domain.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationMapper reservationMapper;

    @Override
    public ReservationDto findByReservationNum(int resNum){
        return reservationMapper.findByReservationNum(resNum);
    }
}
