package com.ezen.springmvc.domain.club.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClubJoinListDto {
    private int joinNum;
    private int clubNum;
    private int memberNum;
    private String joinMemberPhoto;
    private String joinMemberName;
    private String joinMemberPhone;
    private String joinMemberEmail;
    private String joinMemberLevel;
    private String joinMemberInfo;
    private String joinStatus;
}
