package com.ezen.springmvc.domain.community.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 커뮤니티 (소통공간) Dto
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class CommunityDto {
    private String commNum;
    private String commContent;
    private String commDate;
    private String clubNum;
    private String memberNum;
    private String memberName;
}
