package com.ezen.springmvc.domain.match.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateDto {

    private String matchDate;  // 매치날짜
    private String matchInfo; // 전달사항
    private String matchTime; // 매치시간
    private int clubNum = 101; // 클럽번호, createMatch 할 때 clubNum 필요하므로 임시로 101으로 지정
    private int fieldNum; // 구장번호
    private String matchPhoto; // 전략판 사진

}
