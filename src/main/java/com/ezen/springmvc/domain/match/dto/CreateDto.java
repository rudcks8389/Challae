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

<<<<<<< HEAD
    private String matchDate;  // 매치날짜
    private String matchInfo; // 전달사항
    private String matchTime; // 매치시간
    private int clubNum; // 클럽번호
    private int fieldNum; // 구장번호
    private String matchPhoto; // 전략판 사진이름
=======
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
>>>>>>> 1f11d145f106574e0d5099fde22f45caab614fcb

}
