package com.ezen.springmvc.domain.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberDto {
    private String id;
    private String passwd;
    private String name;
    private String email;
    private String regdate;
}
