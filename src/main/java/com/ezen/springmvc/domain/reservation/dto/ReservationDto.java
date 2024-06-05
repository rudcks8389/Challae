package com.ezen.springmvc.domain.reservation.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReservationDto {
    private int resNum;
    private String resDate;
    private int resPrice;
    private String resTime;
    private String resMemo;
    private int memberNum;
    private int fieldNum;




}
