package com.ezen.springmvc.domain.match.dto;

import lombok.*;

//myteam 경기일정 출력을 위한 Dto
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ClubMatchDto {

    private String matchNum; // 매치 번호
    private String matchDate; // 매치 날짜
    private String matchTime; // 매치 시간
    private String clubNum; // 클럽 번호
    private String fieldNum; // 필드 번호
    private String fieldName; // 필드 이름
}
