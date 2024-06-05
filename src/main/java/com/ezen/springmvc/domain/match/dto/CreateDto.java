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
    private String matchDate;  /** 매치날짜 **/
    private String matchInfo; /** 전달사항 **/
    private String matchTime; /** 매치시간 **/
    private int clubNum; /** 클럽번호 **/
    private int fieldNum; /** 구장번호 **/
    private String matchPhoto; /** 전략판 사진이름 **/
    private String matchNum; /** 전략판 매치번호 **/
    private String matchDateForm; /** 매치 날짜폼 **/
    private String fieldName; /** 구장이름 **/
    private String fieldNewaddress; /** 도로명 주소 **/
    private String fieldOldaddress; /** 구 주소 **/
    private String fieldX; /** 전략판 X축 **/
    private String fieldY; /** 전략판 Y축 **/


}
