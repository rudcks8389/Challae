package com.ezen.springmvc.domain.match.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberListDto {
    private int memberNum;
    private String memberName;
    private String memberPhoto;

    
}
