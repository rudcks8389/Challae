package com.ezen.springmvc.domain.match.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateDto {

    private String matchNum; // 매치번호
    private String matchDate;  // 매치날짜
    private String matchInfo; // 전달사항
    private String matchTime; // 매치시간
    private String matchPhoto; // 전략판 사진
    private int clubNum ;//= 101; 브랜치에서 동적인 데이터를 위해 주석_PARK // 클럽번호, createMatch 할 때 clubNum 필요하므로 임시로 101으로 지정_YOON
    private int fieldNum; // 구장번호
    private String fieldName;// 구장이름
    private String fieldNewAddress; // 구장 신주소
    private String fieldX; // 구장 x좌표
    private String fieldY; // 구장 y좌표


}
