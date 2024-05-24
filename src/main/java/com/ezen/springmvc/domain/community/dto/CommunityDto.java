package com.ezen.springmvc.domain.community.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String memberName;
}
