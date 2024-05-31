package com.ezen.springmvc.domain.reservation.mapper;

import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    public ReservationDto findByReservationNum(int resNum);
}
