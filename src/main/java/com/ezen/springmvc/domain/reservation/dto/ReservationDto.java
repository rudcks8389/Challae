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
    private Date resresDate;
    private int resPrice;
    private String resStarttime;
    private String resEndtime;
    private int memberNum;
}
