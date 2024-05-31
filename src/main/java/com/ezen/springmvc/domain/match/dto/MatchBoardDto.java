package com.ezen.springmvc.domain.match.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MatchBoardDto {

    private int mbNum;  // 매치보드 번호
    private String mbName; // 매치보드 이름
    private String mbFile; // 전략판 파일
    private int clubNum; // 클럽 번호
    private String mbType; // 라디오 버튼 종류

}
