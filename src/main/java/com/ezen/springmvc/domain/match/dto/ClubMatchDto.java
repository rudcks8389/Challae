package com.ezen.springmvc.domain.match.dto;

import lombok.*;

/**
 * myteam 페이지 경기일정 출력을 위한 Dto
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ClubMatchDto {

    private String matchNum;
    private String matchDate;
    private String matchTime;
    private String clubNum;
    private String fieldNum;
    private String fieldName;
}
