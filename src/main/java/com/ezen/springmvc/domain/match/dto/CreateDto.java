package com.ezen.springmvc.domain.match.dto;

import lombok.*;

/**
 * 경기 일정 생성을 위한 DTO
 */

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
    private int clubNum; // 클럽번호
    private int fieldNum; // 구장번호
    private String matchPhoto; // 전략판 사진이름

}
