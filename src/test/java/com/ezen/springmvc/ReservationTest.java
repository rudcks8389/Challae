package com.ezen.springmvc;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.dto.MemberSearchCondition;
import com.ezen.springmvc.domain.member.mapper.MemberMapper;
import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
import com.ezen.springmvc.domain.reservation.mapper.ReservationMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class ReservationTest {

    @Autowired
    ReservationMapper reservationMapper;

    @Test
//    @Disabled
    void insertReservationTest() {
        ReservationDto reservationDto = ReservationDto.builder()
                .resDate("2024-06-24")
                .resPrice(100)
                .memberNum(1005)
                .resTime("15:00-16:00")
                .resMemo("예약 메소")
                .build();
        reservationMapper.insertReservation(reservationDto);
        log.info("예약 등록 완료");
    }

    @Test
    void findByReservationNum() {
        ReservationDto reservationDto = reservationMapper.findByReservationNum(6);
        log.info("reservationDto: {}", reservationDto);
    }


}








