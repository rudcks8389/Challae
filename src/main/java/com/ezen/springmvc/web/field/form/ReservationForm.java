package com.ezen.springmvc.web.field.form;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReservationForm {
    private String resNum;
    private String resResDate;
    private int resPrice;
    private String resTime;
    private String resMemo;
    private String resName;
    private String resPhone;
    private int memberNum;
}
