package com.ezen.springmvc.domain.member.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberSearchCondition {
    private String memberId;
    private String name;
    private String email;
}


