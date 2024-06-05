package com.ezen.springmvc.domain.reservation.dto;
import lombok.*;

import java.util.Date;
/**
 * ReservationDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReservationDto {
    private int resNum; /**예약번호**/
    private String resDate; /**예약날짜**/
    private int resPrice; /**예약가격**/
    private String resTime; /**예약시간**/
    private String resMemo; /**메모**/
    private int memberNum; /**회원번호**/
    private int fieldNum; /**구장번호**/
}
