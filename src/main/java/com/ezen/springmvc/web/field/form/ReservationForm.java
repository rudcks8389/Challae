package com.ezen.springmvc.web.field.form;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 구장예약 폼 저장
 */
public class ReservationForm {
    private String resNum; /**예약번호**/
    private String resResDate; /**예약날짜**/
    private int resPrice; /**예약가격**/
    private String resTime; /**예약시간**/
    private String resMemo; /**메모**/
    private String resName; /**예약자이름**/
    private String resPhone; /**예약자전화번호**/
    private int memberNum; /**회원번호**/
}
