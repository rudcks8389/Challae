package com.ezen.springmvc.domain.reservation.service;

import com.ezen.springmvc.domain.reservation.dto.ReservationDto;

public interface ReservationService {
    public ReservationDto findByReservationNum(int resNum);
}
