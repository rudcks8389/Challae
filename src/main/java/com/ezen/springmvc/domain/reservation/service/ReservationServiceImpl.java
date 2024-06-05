package com.ezen.springmvc.domain.reservation.service;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
import com.ezen.springmvc.domain.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * ReservationServiceImpl
 */
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationMapper reservationMapper;

    /**
     * 예약 저장 메서드 추가
     * @param reservationDto
     */
    @Override
    @Transactional
    public void reserveField(ReservationDto reservationDto) {
        reservationMapper.insertReservation(reservationDto);
    }

    /**
     * 예약번호로 값불러오기
     * @param resNum
     * @return
     */
    @Override
    public ReservationDto findByReservationNum(int resNum) {
        return reservationMapper.findByReservationNum(resNum);
    }

    /**
     * 회원번호로 값불러오기
     * @param memberNum
     * @return
     */
    @Override
    public MemberDto findByMemberNum(int memberNum) {
        return reservationMapper.findByMemberNum(memberNum);
    }
}
