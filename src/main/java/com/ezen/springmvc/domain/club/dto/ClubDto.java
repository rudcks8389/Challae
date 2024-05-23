package com.ezen.springmvc.domain.club.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClubDto {
    private String clubNum;
    private String clubInfo;
    private String clubMemberCount;
    private String clubPhoto;
    private String clubLevel;
    private String clubRegdate;
    private String clubLocation;
    private String clubPhone;
    private String clubName;
    private String clubPresident;
}
