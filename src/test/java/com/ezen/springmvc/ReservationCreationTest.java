//package com.ezen.springmvc;
//
//import com.ezen.springmvc.domain.reservation.dto.ResCreateDto;
//import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
//import com.ezen.springmvc.domain.reservation.mapper.ReservationMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//public class ReservationCreationTest {
//    @Test
//    void createReservationTest() {
//        // Mock 객체 생성
//        ReservationMapper reservationMapper = Mockito.mock(ReservationMapper.class);
//
//        // 예약 정보 생성
//        ReservationDto reservationDto = new ReservationDto();
//        reservationDto.setResResDate("2024-06-03");
//        reservationDto.setResPrice(10000);
//        reservationDto.setResTime("12:00");
//        reservationDto.setMemberNum(1);
//        reservationDto.setResMemo("테스트 예약입니다.");
//
//        // ReservationDto를 ResCreateDto로 변환
//        ResCreateDto resCreateDto = new ResCreateDto();
//        resCreateDto.setResResdate(reservationDto.getResResDate());
//        resCreateDto.setResPrice(reservationDto.getResPrice());
//        resCreateDto.setResTime(reservationDto.getResTime());
//        resCreateDto.setMemberNum(reservationDto.getMemberNum());
//        resCreateDto.setResMemo(reservationDto.getResMemo());
//
//        // saveReservation 메서드 호출 시 예상되는 동작 설정
//        Mockito.doNothing().when(reservationMapper).saveReservation(resCreateDto);
//
//        // 테스트 대상 메서드 실행
//        reservationMapper.saveReservation(resCreateDto);
//
//        // 결과 확인
//        System.out.println("예약 등록 완료 : " + reservationDto);
//    }
//}
