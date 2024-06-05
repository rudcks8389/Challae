package com.ezen.springmvc.domain.match.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateDto {

    private String matchNum;
    private String matchDate;
    private String matchDateForm;
    private String matchInfo;
    private String matchTime;
    private String matchPhoto;
    private int clubNum;
    private int fieldNum;
    private String fieldName;
    private String fieldNewaddress;
    private String fieldOldaddress;
    private String fieldX;
    private String fieldY;

}
